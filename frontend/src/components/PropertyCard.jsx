import homeImg from "../assets/home.webp";

export default function PropertyCard({ img, title, city, type }) {
  // Use a default image if img is missing
  const imageUrl = img || homeImg;

 
  return (
    <div style={{
      border: "1px solid #e0e0e0",
      borderRadius: "12px",
      overflow: "hidden",
      margin: "16px 0",
      boxShadow: "0 4px 12px rgba(0, 0, 0, 0.1)",
      transition: "transform 0.2s",
      backgroundColor: "#fff",
      cursor: "pointer"
    }}>
      <img
        src={imageUrl}
        alt={title}
        style={{
          width: "100%",
          height: "300px",
          objectFit: "cover"
        }}
      />
      <div style={{ padding: "16px" }}>
        <h2 style={{
          margin: "0 0 8px",
          fontSize: "20px",
          color: "#333"
        }}>{title}</h2>
        <p style={{ margin: "4px 0", color: "#666" }}>
          <strong>Stadt:</strong> {city}
        </p>
        <p style={{ margin: "4px 0", color: "#666" }}>
          <strong>Typ:</strong> {type}
        </p>
      </div>
    </div>
  );
}
