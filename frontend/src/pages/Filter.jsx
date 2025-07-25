import React, { useState } from "react";
import axios from "axios";
import PageLayout from "../components/PageLayout";
import BottomNav from "../components/BottomNav";
import TopNav from "../components/TopNav";
import { Link } from "react-router-dom";

export default function FilterPage() {
  const [location, setLocation] = useState("");
  const [minPrice, setMinPrice] = useState("");
  const [maxPrice, setMaxPrice] = useState("");
  const [propertyType, setPropertyType] = useState("");
  const [loading, setLoading] = useState("");
  const [results, setResults] = useState([]);
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setResults([]);

    try {
      const token = localStorage.getItem('token'); // Get the token from localStorage

      if (!token) {
        console.warn('Kein JWT-Token gefunden. Kann Filter nicht anwenden.');
        setError('Sie müssen angemeldet sein, um Filter anzuwenden.');
        setLoading(false);
        return; // Stop execution if no token
      }

      const query = new URLSearchParams();
      if (location) query.append("city", location);
      if (minPrice) query.append("minPrice", minPrice);
      if (maxPrice) query.append("maxPrice", maxPrice);
      if (propertyType) query.append("type", propertyType);

      const response = await axios.get(`http://${import.meta.env.VITE_BACKEND_HOST}:${import.meta.env.VITE_BACKEND_PORT}/api/estate?${query.toString()}`, {
        headers: {
          'Authorization': `Bearer ${token}` // <--- ADD THIS LINE: Send the Authorization header
        }
      });
      console.log("Gefilterte Inserate:", response.data);
      setResults(response.data);
    } catch (err) { // Changed 'error' to 'err' to avoid conflict with state variable
      console.error("Fehler beim Abrufen der Inserate:", err);
      setError('Fehler beim Abrufen der Inserate.'); // Generic error message
      // Specific error handling for 403/401
      if (err.response && (err.response.status === 401 || err.response.status === 403)) {
        setError('Sitzung abgelaufen oder nicht autorisiert. Bitte melden Sie sich erneut an.');
        // Optional: Token löschen und zum Login umleiten
        // localStorage.removeItem('token');
        // window.location.href = '/login';
      }
    } finally {
      setLoading(false);
    }
  };
  return (
    <div>
      <TopNav />
      <PageLayout
        title="Filter"
        subtitle="Grenzen Sie Ihre Suche ein"
        showChatLink={false}
        showBottomNav={true}
      >
        <form onSubmit={handleSubmit} style={{ display: "flex", flexDirection: "column", gap: "20px" }}>
          <div>
            <label htmlFor="location" style={{ display: "block", marginBottom: "8px" }}>
              Ort
            </label>
            <input
              type="text"
              id="location"
              placeholder="z. B. Berlin"
              value={location}
              onChange={(e) => setLocation(e.target.value)}
              style={inputStyle}
            />
          </div>

          <div>
            <label style={{ display: "block", marginBottom: "8px" }}>Preisbereich (€)</label>
            <div style={{ display: "flex", gap: "10px" }}>
              <input
                type="number"
                placeholder="Min"
                value={minPrice}
                onChange={(e) => setMinPrice(e.target.value)}
                style={inputStyle}
              />
              <input
                type="number"
                placeholder="Max"
                value={maxPrice}
                onChange={(e) => setMaxPrice(e.target.value)}
                style={inputStyle}
              />
            </div>
          </div>

          <div>
            <label htmlFor="propertyType" style={{ display: "block", marginBottom: "8px" }}>
              Immobilientyp
            </label>
            <select
              id="propertyType"
              value={propertyType}
              onChange={(e) => setPropertyType(e.target.value)}
              style={inputStyle}
            >
              <option value="">Alle</option>
              <option value="CoHousing">CoHousing</option>
              <option value="Mehrgenerationwohnung">Mehrgenerationwohnung</option>
              <option value="Mikroappartement">Mikroappartement</option>
              <option value="Baugruppe">Baugruppe</option>
            </select>
          </div>

          <button type="submit" style={buttonStyle}>
            Filter anwenden
          </button>
        </form>
        {error && <p style={{ color: "red" }}>{error}</p>}

        <div style={{ marginTop: "40px" }}>
          {loading && <p>🔄 Lade Inserate...</p>}
          {results.length > 0 && (
            <div>
              <h3>🎯 Gefundene Inserate ({results.length})</h3>
              <ul style={{ listStyle: "none", padding: 0 }}>
                {results.map((item) => (
                  <Link
                    to={`/listing/${item.id}`}
                    key={item.id}
                    style={{ textDecoration: "none", color: "inherit" }}
                  >
                    <li
                      style={{
                        padding: "15px",
                        border: "1px solid #ddd",
                        borderRadius: "8px",
                        marginBottom: "15px",
                        background: "#f9f9f9",
                        cursor: "pointer",
                      }}
                    >
                      <h4>{item.titel}</h4>
                      <p><strong>Stadt:</strong> {item.address.city}</p>
                      <p><strong>Kaltmiete:</strong> {item.rentCold} €</p>
                      <p><strong>Typ:</strong> {item.type}</p>
                      <p>{item.description}</p>
                    </li>
                  </Link>
                ))}
              </ul>
            </div>
          )}
          {!loading && results.length === 0 && <p>🔍 Keine Inserate gefunden.</p>}
        </div>
      </PageLayout>
      <BottomNav title="Filter" />
    </div>

  );
}
const inputStyle = {
  width: "100%",
  padding: "10px",
  borderRadius: "8px",
  border: "1px solid #ccc",
};

const buttonStyle = {
  padding: "12px",
  backgroundColor: "black",
  color: "#fff",
  border: "none",
  borderRadius: "8px",
  fontSize: "16px",
  cursor: "pointer",
};
