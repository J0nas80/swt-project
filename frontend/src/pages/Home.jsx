import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import PageLayout from "../components/PageLayout";
import PropertyCard from "../components/PropertyCard";
import Sidebar from "../components/Sidebar";
import TopNav from "../components/TopNav";
import BottomNav from "../components/BottomNav";
import ChatSidebar from "../components/ChatSidebar";

export default function Home() {
  const navigate = useNavigate();
  const [chatOpen, setChatOpen] = useState(false);
  const [properties, setProperties] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // Replace URL with your actual backend route
    axios.get("http://localhost:8080/api/inserate")
      .then((response) => {
        setProperties(response.data);
      })
      .catch((error) => {
        console.error("Fehler beim Laden der Inserate:", error);
      })
      .finally(() => setLoading(false));
  }, []);

  return (
    <div>
      <TopNav
        leftIcon={
          <button onClick={() => setChatOpen(!chatOpen)} style={chatButtonStyle}>
            💬
          </button>
        }
        rightIcon={<Sidebar />}
      />


      <ChatSidebar isOpen={chatOpen} onClose={() => setChatOpen(false)} />

      <PageLayout>
        {loading ? (
          <p>Inserate werden geladen...</p>
        ) : properties.length > 0 ? (
          properties.map((property) => (
            <Link
              to={`/listing/${property.id}`}
              key={property.id}
              style={{ textDecoration: "none", color: "inherit" }}
            >
              <PropertyCard {...property} />
            </Link>
          ))
        ) : (
          <p>Keine Inserate gefunden.</p>
        )}

        <div style={actionContainerStyle}>
          <button style={buttonStyle}>❌</button>
          <button style={buttonStyle}>❤️</button>
          <button style={buttonStyle}>📥</button>
        </div>
      </PageLayout>

      <BottomNav title="Home" onBack={() => navigate(-1)} />
    </div>
  );
}

const chatButtonStyle = {
  background: "none",
  fontSize: "24px",
  border: "none",
  cursor: "pointer",
};

const actionContainerStyle = {
  display: "flex",
  justifyContent: "center",
  gap: "30px",
  marginTop: "30px",
};

const buttonStyle = {
  fontSize: "24px",
  padding: "12px",
  borderRadius: "50%",
  border: "1px solid #ccc",
  background: "#fff",
  cursor: "pointer",
  boxShadow: "0 2px 5px rgba(0,0,0,0.1)",
};
