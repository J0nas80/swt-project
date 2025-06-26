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
    axios.get(`http://localhost:8080/api/inserate/${id}`)
      .then(res => {
        setListing(res.data);
      });

    const token = localStorage.getItem("token");
    if (token) {
      axios.get("http://localhost:8080/api/user", {
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
      `http://localhost:8080/api/user/save/${id}`,
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
        <p><strong>Stadt:</strong> {listing.city}</p>
        <p><strong>Typ:</strong> {listing.type}</p>
        <p><strong>Beschreibung:</strong> {listing.description}</p>
        <p><strong>Preis:</strong> {listing.price} €</p>

        <button onClick={handleSave} disabled={isSaved}>
          {isSaved ? "Gespeichert" : "Speichern"}
        </button>
      </div>
      <BottomNav />
    </div>
  );
}
