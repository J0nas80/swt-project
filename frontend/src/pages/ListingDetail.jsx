import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom"; // Added useNavigate for potential redirection
import axios from "axios";
import TopNav from "../components/TopNav";
import BottomNav from "../components/BottomNav";
import homeImg from "../assets/home.webp";

export default function ListingDetail() {
  const { id } = useParams(); // ID from URL
  const navigate = useNavigate(); // Initialize useNavigate
  const [listing, setListing] = useState(null);
  const [isSaved, setIsSaved] = useState(false);
  const [loading, setLoading] = useState(true); // Added loading state
  const [error, setError] = useState(null);   // Added error state

  useEffect(() => {
    const fetchListingAndUserData = async () => {
      setLoading(true); // This line should now be recognized
      setError(null);
      const token = localStorage.getItem("token"); // Get the token once

      if (!token) {
        setError("Sie müssen angemeldet sein, um Inseratsdetails zu sehen.");
        setLoading(false);
        // Optionally redirect to login page
        // navigate('/login'); // Uncomment if you want to redirect
        return;
      }

      try {
        // --- CRITICAL FIX: Add Authorization header to fetching listing details ---
        const listingResponse = await axios.get(`http://${process.env.BACKEND_HOST}:${process.env.BACKEND_PORT}/api/estate/${id}`, {
          headers: { Authorization: `Bearer ${token}` }
        });
        setListing(listingResponse.data);

        // Fetch user data to check saved listings
        const userResponse = await axios.get(`http://${process.env.BACKEND_HOST}:${process.env.BACKEND_PORT}/api/user/me`, {
          headers: { Authorization: `Bearer ${token}` }
        });
        const savedIds = userResponse.data.saved || []; // Assuming 'saved' is the field name for saved listings
        setIsSaved(savedIds.includes(Number(id))); // Convert id to Number for comparison if savedIds are numbers
      } catch (err) {
        console.error("Fehler beim Laden der Inseratsdetails oder Benutzerdaten:", err);
        setError('Fehler beim Laden der Inseratsdetails.');
        if (err.response && (err.response.status === 401 || err.response.status === 403)) {
          setError('Sitzung abgelaufen oder nicht autorisiert. Bitte melden Sie sich erneut an.');
          // localStorage.removeItem('token');
          // navigate('/login'); // Uncomment if you want to redirect
        }
      } finally {
        setLoading(false); // This line should now be recognized
      }
    };

    fetchListingAndUserData();
  }, [id, navigate]); // Added navigate to dependency array as it's used in comments

  const handleSave = async () => { // Made async to await the post request
    const token = localStorage.getItem("token");
    if (!token) {
      alert("Bitte zuerst einloggen");
      return;
    }

    try {
      await axios.post( // Use await here
        `http://${process.env.BACKEND_HOST}:${process.env.BACKEND_PORT}/api/user/me/saved/${id}`,
        {},
        { headers: { Authorization: `Bearer ${token}` } }
      );
      setIsSaved(true);
      alert("Inserat gespeichert");
    } catch (err) {
      console.error("Fehler beim Speichern des Inserats:", err);
      alert("Fehler beim Speichern des Inserats.");
      if (err.response && (err.response.status === 401 || err.response.status === 403)) {
        alert('Sitzung abgelaufen oder nicht autorisiert. Bitte melden Sie sich erneut an.');
      }
    }
  };

  if (loading) return <p>Lade Inserat...</p>;
  if (error) return <p style={{ color: 'red' }}>{error}</p>;
  if (!listing) return <p>Inserat nicht gefunden.</p>; // Handle case where listing is null after loading

  const imageUrl = listing.img
    ? `http://${process.env.BACKEND_HOST}:${process.env.BACKEND_PORT}/uploads/${listing.img}`
    : homeImg;

  return (
    <div>
      <TopNav />
      <div style={containerStyle}>
        <img src={imageUrl} alt="Wohnung" style={imageStyle} />

        <h2 style={titleStyle}>{listing.title || listing.titel}</h2>
        <h3 style={typeStyle}>{listing.type}</h3>

        <div style={detailGrid}>
          <p><strong>Beschreibung:</strong> {listing.description}</p>
          <p><strong>Fläche:</strong> {listing.area} m²</p>
          <p><strong>Zimmer:</strong> {listing.roomCount}</p>
          <p><strong>Kaltmiete:</strong> {listing.rentCold} €</p>
          <p><strong>Verfügbar ab:</strong> {listing.availableFrom}</p>
          {listing.address && (
            <p>
              <strong>Adresse:</strong> {listing.address.street} {listing.address.houseNumber}, {listing.address.postalCode} {listing.address.city}
            </p>
          )}
          {listing.landlord?.email && (
            <p><strong>Vermieter:</strong> {listing.landlord.email}</p>
          )}
        </div>

        <button onClick={handleSave} disabled={isSaved} style={saveButtonStyle}>
          {isSaved ? "Gespeichert" : "Inserat speichern"}
        </button>
      </div>
      <BottomNav />
    </div>
  );
}

const containerStyle = {
  padding: "24px",
  paddingTop: "80px",
  maxWidth: "700px",
  margin: "0 auto",
  paddingBottom: "80px",
  //fontFamily: "Arial, sans-serif",
};

const imageStyle = {
  width: "100%",
  height: "300px",
  objectFit: "cover",
  borderRadius: "12px",
  boxShadow: "0 4px 12px rgba(0,0,0,0.1)",
  marginBottom: "20px",
};

const titleStyle = {
  fontSize: "28px",
  marginBottom: "8px",
};

const typeStyle = {
  fontSize: "18px",
  color: "#666",
  marginBottom: "16px",
};

const detailGrid = {
  display: "flex",
  flexDirection: "column",
  gap: "5px",
  fontSize: "16px",
};

const saveButtonStyle = {
  marginTop: "30px",
  padding: "12px 24px",
  backgroundColor: "#007bff",
  color: "#fff",
  border: "none",
  borderRadius: "8px",
  fontSize: "16px",
  cursor: "pointer",
};
