// File: src/pages/Saved.jsx
// Description: This page displays saved listings for the user.
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import PageLayout from "../components/PageLayout";
import TopNav from "../components/TopNav";
import BottomNav from "../components/BottomNav";
import Sidebar from "../components/Sidebar";

export default function Saved() {
  const navigate = useNavigate();
  const [savedListings, setSavedListings] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (!token) {
      setError("Nicht eingeloggt.");
      return;
    }

    axios.get("http://localhost:8080/api/user/me/saved", {
      headers: { Authorization: `Bearer ${token}` },
    })
    .then(res => {
      setSavedListings(res.data);
    })
    .catch(err => {
      console.error(err);
      setError("Fehler beim Laden der gespeicherten Inserate.");
    });
  }, []);

  return (
    <div>
      <TopNav rightIcon={<Sidebar />} />
      <PageLayout>
        {error ? (
          <p style={{ color: "red", padding: "20px" }}>{error}</p>
        ) : savedListings.length > 0 ? (
          savedListings.map(item => (
            <div
              key={item.id}
              style={{
                display: "flex",
                gap: "12px",
                borderBottom: "2px solid black",
                padding: "10px 0",
                alignItems: "center",
              }}
              onClick={() => navigate(`/listing/${item.id}`)}
            >
              <img src={item.img || "/default.jpg"} alt={item.titel} style={{ width: "100px", borderRadius: "8px" }} />
              <div>
                <h3>{item.titel}</h3>
                <p>{item.address?.city}</p>
                <p style={{ fontSize: "14px", color: "gray" }}>{item.rentCold} €</p>
              </div>
            </div>
          ))
        ) : (
          <p style={{ padding: "20px" }}>Keine gespeicherten Inserate</p>
        )}
      </PageLayout>
      <BottomNav title="Gespeichert" onBack={() => navigate(-1)} />
    </div>
  );
}

