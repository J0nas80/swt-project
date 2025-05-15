import { Link } from "react-router-dom";

export default function HomePage() {
  return (
    <div>
      <h1>Willkommen in der App</h1>
      <p>Was möchten Sie tun?</p>
      <nav>
        <ul>
          <li><Link to="/add-listing">Inserat hinzufügen</Link></li>
          <li><Link to="/search">Inserate suchen</Link></li>
        </ul>
      </nav>
    </div>
  );
}