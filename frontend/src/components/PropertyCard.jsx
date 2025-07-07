import homeImg from "../assets/home.webp";

export default function PropertyCard({ img, title, city, type }) {
  // Use a fallback image if img is missing
  const imageUrl = img || homeImg;

  return (
    <div style={{ border: "3px solid black", margin: "10px 0", padding: "10px" }}>
      <img src={imageUrl} alt={title} width="100%" />
      <h2>{title}</h2>
      <p><strong>Stadt:</strong> {city}</p>
      <p><strong>Typ:</strong> {type}</p>
    </div>
  );
}
