import { useNavigate } from "react-router-dom";
import TopNav from "../components/TopNav";
import BottomNav from "../components/BottomNav";
import PageLayout from "../components/PageLayout";

export default function InseratCreate() {
  const navigate = useNavigate();

  const dummyData = [
    { image: "../assets/home.webp", category: "Auto", info: "BMW 320d, 2015" },
    { image: "../assets/home.webp", category: "Haus", info: "3-Zimmer Wohnung, Berlin" },
  ];

  return (
    <div>
      <TopNav />
      <PageLayout title="Inserat Erstellen" subtitle="Ihre Inserate verwalten">
        <table style={{ width: "100%", borderCollapse: "collapse", marginBottom: "20px" }}>
          <thead>
            <tr style={{ background: "#eee" }}>
              <th style={thStyle}>Bild</th>
              <th style={thStyle}>Kategorie</th>
              <th style={thStyle}>Infos</th>
            </tr>
          </thead>
          <tbody>
            {dummyData.map((row, idx) => (
              <tr key={idx}>
                <td style={tdStyle}>
                  <img src={row.image} alt="Item" style={{ width: "60px", borderRadius: "8px" }} />
                </td>
                <td style={tdStyle}>{row.category}</td>
                <td style={tdStyle}>{row.info}</td>
              </tr>
            ))}
          </tbody>
        </table>

        <button
          onClick={() => navigate("/inserat/new")}
          style={{
            padding: "10px 20px",
            fontSize: "16px",
            background: "black",
            color: "#fff",
            border: "none",
            borderRadius: "6px",
            cursor: "pointer",
          }}
        >
          ➕ Hinzufügen
        </button>
      </PageLayout>
      <BottomNav title="InserateCreate" />
    </div>
  );
}

const thStyle = {
  padding: "10px",
  textAlign: "left",
  borderBottom: "1px solid #ccc",
};

const tdStyle = {
  padding: "10px",
  borderBottom: "1px solid #eee",
};
