import homeImg from "../assets/home.webp";

export default function PropertyCard({ image, title, subtitle, details, moreInfoLink }) {
    return (
        <div style={{ border: "3px solid black", margin: "10px 0", padding: "10px" }}>
            <img src={homeImg} alt={title} width="100%" />
            <h2>{title}</h2>
            <p>{subtitle}</p>
            <p>{details}</p>
            <a href={moreInfoLink}>More Info</a>
        </div>
    );
}
