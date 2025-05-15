import React from "react";
import PageLayout from "../components/PageLayout";
import BottomNav from "../components/BottomNav";
import TopNav from "../components/TopNav";

export default function FilterPage() {
  return (
    <div>
      <TopNav />
      <PageLayout
        title="Filter"
        subtitle="Grenzen Sie Ihre Suche ein"
        showChatLink={false}
        showBottomNav={true}
      >
        <form style={{ display: "flex", flexDirection: "column", gap: "20px" }}>
          <div>
            <label htmlFor="location" style={{ display: "block", marginBottom: "8px" }}>
              Ort
            </label>
            <input
              type="text"
              id="location"
              placeholder="z. B. Berlin"
              style={{
                width: "100%",
                padding: "10px",
                borderRadius: "8px",
                border: "1px solid #ccc",
              }}
            />
          </div>

          <div>
            <label style={{ display: "block", marginBottom: "8px" }}>Preisbereich (€)</label>
            <div style={{ display: "flex", gap: "10px" }}>
              <input
                type="number"
                placeholder="Min"
                style={{
                  flex: 1,
                  padding: "10px",
                  borderRadius: "8px",
                  border: "1px solid #ccc",
                }}
              />
              <input
                type="number"
                placeholder="Max"
                style={{
                  flex: 1,
                  padding: "10px",
                  borderRadius: "8px",
                  border: "1px solid #ccc",
                }}
              />
            </div>
          </div>

          <div>
            <label htmlFor="propertyType" style={{ display: "block", marginBottom: "8px" }}>
              Immobilientyp
            </label>
            <select
              id="propertyType"
              style={{
                width: "100%",
                padding: "10px",
                borderRadius: "8px",
                border: "1px solid #ccc",
              }}
            >
              <option value="">Alle</option>
              <option value="apartment">Wohnung</option>
              <option value="house">Haus</option>
              <option value="studio">Studio</option>
            </select>
          </div>

          <div>
            <label style={{ display: "block", marginBottom: "8px" }}>Ausstattung</label>
            <div style={{ display: "flex", flexDirection: "column", gap: "10px" }}>
              <label>
                <input type="checkbox" /> Balkon
              </label>
              <label>
                <input type="checkbox" /> Garten
              </label>
              <label>
                <input type="checkbox" /> Garage
              </label>
            </div>
          </div>

          <button
            type="submit"
            style={{
              padding: "12px",
              backgroundColor: "black",
              color: "#fff",
              border: "none",
              borderRadius: "8px",
              fontSize: "16px",
              cursor: "pointer",
            }}
          >
            Filter anwenden
          </button>
        </form>
      </PageLayout>
      <BottomNav title="Filter"/>
    </div>

  );
}
