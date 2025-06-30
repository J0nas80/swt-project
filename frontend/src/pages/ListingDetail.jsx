import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import TopNav from "../components/TopNav";
import BottomNav from "../components/BottomNav";

export default function ListingDetail() {
  const { id } = useParams(); // ID from URL
  const [listing, setListing] = useState(null);
  const [isSaved, setIsSaved] = useState(false);

  useEffect(() => {
    axios.get(`http://localhost:8080/api/estate/${id}`)
      .then(res => {
        setListing(res.data);
      });

    const token = localStorage.getItem("token");
    if (token) {
      axios.get("http://localhost:8080/api/user/me", {
        headers: { Authorization: `Bearer ${token}` }
      }).then(res => {
        const savedIds = res.data.savedListings || [];
        setIsSaved(savedIds.includes(id));
      });
    }
  }, [id]);

  const handleSave = () => {
    const token = localStorage.getItem("token");
    if (!token) {
      alert("Bitte zuerst einloggen");
      return;
    }

    axios.post(
      `http://localhost:8080/api/user/me/saved/${id}`,
      {},
      { headers: { Authorization: `Bearer ${token}` } }
    ).then(() => {
      setIsSaved(true);
      alert("Inserat gespeichert");
    });
  };

  if (!listing) return <p>Lade Inserat...</p>;

  return (
    <div>
      <TopNav />
      <div style={{ padding: "20px" }}>
        <h2>{listing.title}</h2>
        <h3>{listing.type}</h3>
        <p><strong>Beschreibung:</strong> {listing.description}</p>
        <p><strong>Fläche:</strong> {listing.area} m²</p>
        <p><strong>Zimmer:</strong> {listing.roomCount}</p>
        <p><strong>Kaltmiete:</strong> {listing.rentCold} €</p>
        <p><strong>Verfügbar ab:</strong> {listing.availableFrom}</p>
        <p><strong>Adresse:</strong> {listing.street} {listing.houseNumber}, {listing.postalCode} {listing.city}</p>
        {listing.landlord?.username && (
          <p><strong>Vermieter:</strong> {listing.landlord.username}</p>
        )}
        {listing.img && (
          <img src={`http://localhost:8080/uploads/${listing.img}`} alt="Wohnung" style={{ maxWidth: '100%', height: 'auto', marginTop: '10px' }} />
        )}

        <button onClick={handleSave} disabled={isSaved}>
          {isSaved ? "Gespeichert" : "Speichern"}
        </button>
      </div>
      <BottomNav />
    </div>
  );
}
