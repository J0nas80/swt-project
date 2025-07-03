import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom"; // Added useNavigate for potential redirection
import axios from "axios";
import TopNav from "../components/TopNav";
import BottomNav from "../components/BottomNav";

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
        const listingResponse = await axios.get(`http://localhost:8080/api/estate/${id}`, {
          headers: { Authorization: `Bearer ${token}` }
        });
        setListing(listingResponse.data);

        // Fetch user data to check saved listings
        const userResponse = await axios.get("http://localhost:8080/api/user/me", {
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
        `http://localhost:8080/api/user/me/saved/${id}`,
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
        // localStorage.removeItem('token');
        // navigate('/login');
      }
    }
  };

  if (loading) return <p>Lade Inserat...</p>;
  if (error) return <p style={{ color: 'red' }}>{error}</p>;
  if (!listing) return <p>Inserat nicht gefunden.</p>; // Handle case where listing is null after loading

  return (
    <div>
      <TopNav />
      <div style={{ padding: "20px", paddingTop: "70px" }}>
        {/* Check for listing.title vs listing.titel based on your backend */}
        <h2>{listing.title || listing.titel}</h2> {/* Use 'title' if you changed backend, fallback to 'titel' */}
        <h3>{listing.type}</h3>
        <p><strong>Beschreibung:</strong> {listing.description}</p>
        <p><strong>Fläche:</strong> {listing.area} m²</p>
        <p><strong>Zimmer:</strong> {listing.roomCount}</p>
        <p><strong>Kaltmiete:</strong> {listing.rentCold} €</p>
        <p><strong>Verfügbar ab:</strong> {listing.availableFrom}</p>
        {/* Assuming address is an object with street, houseNumber, postalCode, city */}
        {listing.address && (
          <p><strong>Adresse:</strong> {listing.address.street} {listing.address.houseNumber}, {listing.address.postalCode} {listing.address.city}</p>
        )}
        {listing.landlord?.email && ( // Assuming landlord has an email or other identifier
          <p><strong>Vermieter:</strong> {listing.landlord.email}</p>
        )}
        {listing.img && (
          // Ensure your backend serves images from this path or adjust accordingly
          <img src={`http://localhost:8080/uploads/${listing.img}`} alt="Wohnung" style={{ maxWidth: '100%', height: 'auto', marginTop: '10px' }} />
        )}

        <button onClick={handleSave} disabled={isSaved} style={saveButtonStyle}>
          {isSaved ? "Gespeichert" : "Speichern"}
        </button>
      </div>
      <BottomNav />
    </div>
  );
}

const saveButtonStyle = {
  padding: '10px 20px',
  backgroundColor: '#007bff',
  color: 'white',
  border: 'none',
  borderRadius: '5px',
  cursor: 'pointer',
  marginTop: '20px',
};
