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
  const [error, setError] = useState(null); // Added error state

  useEffect(() => {
    const fetchProperties = async () => { // Renamed from fetchEstates to match properties state
      try {
        setLoading(true);
        setError(null); // Clear previous errors

        const token = localStorage.getItem('token'); // Get the token from localStorage

        if (!token) {
          console.warn('Kein JWT-Token gefunden. Kann Inserate nicht laden.');
          setError('Sie müssen angemeldet sein, um Inserate zu sehen.');
          setLoading(false);
          return; // Stop execution if no token
        }

        // Fetch all properties from the API
        const response = await axios.get("http://localhost:8080/api/estate/all", {
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

  // Callback function for the Filter component to update properties
  const handleFilterApply = (filteredData) => {
    setProperties(filteredData);
  };

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
        <h2>{properties.length} verfügbare Inserate</h2>

        {loading ? (
          <p>Inserate werden geladen...</p>
        ) : error ? ( // Display error message if there's an error
          <p style={{ color: 'red' }}>{error}</p>
        ) : properties.length > 0 ? (
          <div style={estateListStyle}> {/* Added a style div for grid layout */}
            {properties.map((property) => (
              <Link
                to={`/listing/${property.id}`}
                key={property.id}
                style={{ textDecoration: "none", color: "inherit" }}
              >
                <PropertyCard
                  title={property.titel}
                  city={property.address?.city}
                  type={property.type}
                  img={property.img}
                />
              </Link>
            ))}
          </div>
        ) : (
          <p>Keine Inserate gefunden.</p>
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
const estateListStyle = {
  //display: 'grid',
  gridTemplateColumns: 'repeat(auto-fit, minmax(300px, 1fr))',
  gap: '20px',
  marginTop: '20px',
  width: '100%',
  maxWidth: '1200px',
};
