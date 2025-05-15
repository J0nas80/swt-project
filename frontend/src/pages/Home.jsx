import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import PageLayout from "../components/PageLayout";
import PropertyCard from "../components/PropertyCard";
import Sidebar from "../components/Sidebar";
import TopNav from "../components/TopNav";
import BottomNav from "../components/BottomNav";
import properties from "../data/properties.json";
import ChatSidebar from "../components/ChatSidebar";

export default function Home() {
  const navigate = useNavigate();
  const [chatOpen, setChatOpen] = useState(false);

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
        {properties.map((property) => (
          <PropertyCard key={property.id} {...property} />
        ))}

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
