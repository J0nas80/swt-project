// src/pages/Saved.jsx
import PageLayout from "../components/PageLayout";
import TopNav from "../components/TopNav";
import BottomNav from "../components/BottomNav";
import Sidebar from "../components/Sidebar";
import properties from "../data/properties.json";
import { useNavigate } from "react-router-dom";
import myImage from "../assets/home.webp"; 

export default function InProgress() {
  const navigate = useNavigate();
  const savedItems = properties.slice(0, 3); 

  return (
    <div>
      <TopNav rightIcon={<Sidebar />} />
      <PageLayout>
        {savedItems.map((item) => (
          <div
            key={item.id}
            style={{
              display: "flex",
              gap: "12px",
              borderBottom: "2px solid black",
              padding: "10px 0",
              alignItems: "center",
            }}
          >
            <img src={myImage} alt={item.title} style={{ width: "100px", borderRadius: "8px" }} />
            <div>
              <h3>{item.title}</h3>
              <p>{item.subtitle}</p>
              <p style={{ fontSize: "14px", color: "gray" }}>{item.details}</p>
            </div>
          </div>
        ))}
      </PageLayout>
      <BottomNav title="Verlauf" onBack={() => navigate(-1)} />
    </div>
  );
}
