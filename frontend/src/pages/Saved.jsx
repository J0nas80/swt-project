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

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (!token) return;

    axios.get("http://localhost:8080/api/user", {
      headers: { Authorization: `Bearer ${token}` }
    }).then(res => {
      const savedIds = res.data.savedListings || [];
      return axios.get("http://localhost:8080/api/inserate");
    }).then(res => {
      const allListings = res.data;
      const savedIds = JSON.parse(localStorage.getItem("saved")) || []; // optional fallback
      const filtered = allListings.filter(item => savedIds.includes(item.id));
      setSavedListings(filtered);
    }).catch(err => console.error(err));
  }, []);

  return (
    <div>
      <TopNav rightIcon={<Sidebar />} />
      <PageLayout>
        {savedListings.length > 0 ? (
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
              <img src={item.imageUrl || "/default.jpg"} alt={item.title} style={{ width: "100px", borderRadius: "8px" }} />
              <div>
                <h3>{item.title}</h3>
                <p>{item.city}</p>
                <p style={{ fontSize: "14px", color: "gray" }}>{item.price} €</p>
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

