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
  const [error, setError] = useState(null); 
  const [currentIndex, setCurrentIndex] = useState(0);

  useEffect(() => {
    const fetchProperties = async () => { // Renamed from fetchEstates to match properties state
      try {
        setLoading(true);
        setError(null); // Reset error state before fetching

        const token = localStorage.getItem('token'); // Get the token from localStorage

        if (!token) {
          console.warn('Kein JWT-Token gefunden. Kann Inserate nicht laden.');
          setError('Sie müssen angemeldet sein, um Inserate zu sehen.');
          setLoading(false);
          return; // Stop execution if no token
        }

        // Fetch all properties from the API
        const response = await axios.get(`http://${process.env.BACKEND_HOST}:${process.env.BACKEND_PORT}/api/estate/all`, {
          headers: {
            'Authorization': `Bearer ${token}` 
          }
        });
        setProperties(response.data);
      } catch (err) {
        console.error("Fehler beim Laden der Inserate:", err);
        setError('Fehler beim Laden der Inserate.'); 
        if (err.response && (err.response.status === 401 || err.response.status === 403)) {
          setError('Sitzung abgelaufen oder nicht autorisiert. Bitte melden Sie sich erneut an.');
          // Optional: Token löschen und zum Login umleiten
          // localStorage.removeItem('token');
          // navigate('/login');
        }
      } finally {
        setLoading(false);
      }
    };

    fetchProperties();
  }, []); // Empty dependency array means this runs once on component mount

  const handleSkip = () => {
    if (currentIndex < properties.length - 1) {
      setCurrentIndex(currentIndex + 1);
    } else {
      setCurrentIndex(0); // Reset to the first property if at the end
      alert("Keine weiteren Inserate. Beginne von vorne.");
    }
  };
  const handleSave = async () => {
    const property = properties[currentIndex];
    const token = localStorage.getItem("token");

    try {
      await axios.post(
        `http://${process.env.BACKEND_HOST}:${process.env.BACKEND_PORT}/api/user/me/saved/${property.id}`,
        {},
        { headers: { Authorization: `Bearer ${token}` } }
      );
      alert("Inserat gespeichert");
      handleSkip(); // direkt zum nächsten Inserat
    } catch (err) {
      console.error("Fehler beim Speichern:", err);
      alert("Fehler beim Speichern.");
    }
  };
  const buttonRowStyle = {
    display: "flex",
    justifyContent: "center",
    gap: "50px",
    marginTop: "30px"
  };

  const skipButtonStyle = {
    fontSize: "40px",
    padding: "10px 20px",
    backgroundColor: "#f8d7da",
    border: "none",
    borderRadius: "12px",
    cursor: "pointer"
  };

  const saveButtonStyle = {
    fontSize: "40px",
    padding: "10px 20px",
    backgroundColor: "#d4edda",
    border: "none",
    borderRadius: "12px",
    cursor: "pointer"
  };
  const currentProperty = properties[currentIndex];

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
        <h2>{properties.length - currentIndex} verbleibende Inserate</h2>

        {loading ? (
          <p>Inserate werden geladen...</p>
        ) : error ? (
          <p style={{ color: "red" }}>{error}</p>
        ) : currentProperty ? (
          <div style={{ textAlign: "center", marginTop: "20px" }}>
            <Link to ={`/listing/${currentProperty.id}`}
              style={{ textDecoration: "none", color: "inherit" }}>
            <PropertyCard
              title={currentProperty.titel}
              city={currentProperty.address?.city}
              type={currentProperty.type}
              img={currentProperty.img}
            />
            </Link>
            <div style={actionContainerStyle}>
              <button onClick={handleSkip} style={buttonStyle}>❌</button>
              <button onClick={handleSave} style={buttonStyle}>📥</button>
            </div>
          </div>
        ) : (
          <p>Alle Inserate durchgesehen.</p>
        )}
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
