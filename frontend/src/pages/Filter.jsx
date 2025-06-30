import React, { useState } from "react";
import axios from "axios";
import PageLayout from "../components/PageLayout";
import BottomNav from "../components/BottomNav";
import TopNav from "../components/TopNav";

export default function FilterPage() {
  const [location, setLocation] = useState("");
  const [minPrice, setMinPrice] = useState("");
  const [maxPrice, setMaxPrice] = useState("");
  const [propertyType, setPropertyType] = useState("");
  const [loading, setLoading] = useState("");
  const [results, setResults] = useState([]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setResults([]);

    try {
      const query = new URLSearchParams({
        city: location,
        minPrice,
        maxPrice,
        type: propertyType,
      });

      const response = await axios.get(`http://localhost:8080/api/estate?${query.toString()}`);
      console.log("Gefilterte Inserate:", response.data);
      setResults(response.data);
    } catch (error) {
      console.error("Fehler beim Abrufen der Inserate:", error);
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

      <div style={{ marginTop: "40px" }}>
          {loading && <p>🔄 Lade Inserate...</p>}
          {results.length > 0 && (
            <div>
              <h3>🎯 Gefundene Inserate ({results.length})</h3>
              <ul style={{ listStyle: "none", padding: 0 }}>
                {results.map((item) => (
                  <li
                    key={item.id}
                    style={{
                      padding: "15px",
                      border: "1px solid #ddd",
                      borderRadius: "8px",
                      marginBottom: "15px",
                      background: "#f9f9f9",
                    }}
                  >
                    <h4>{item.title}</h4>
                    <p><strong>Stadt:</strong> {item.city}</p>
                    <p><strong>Kaltmiete:</strong> {item.rentCold} €</p>
                    <p><strong>Typ:</strong> {item.type}</p>
                    <p>{item.description}</p>
                  </li>
                ))}
              </ul>
            </div>
          )}
          {!loading && results.length === 0 && <p>🔍 Keine Inserate gefunden.</p>}
        </div>  
      </PageLayout>
      <BottomNav title="Filter"/>
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