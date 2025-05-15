import { useNavigate } from "react-router-dom";
import { useState } from "react";

export default function LoginPage() {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = (e) => {
    e.preventDefault();
    // Here you'd check credentials, but for now we simulate success
    navigate("/home");
  };

  return (
    <form onSubmit={handleLogin}>
    <h1>Willkommen bei der Wohnplattform 👋</h1>
    <p>Bitte logge dich ein, um fortzufahren:</p>
      <h2>Login</h2>
      <label>
        E-Mail:
        <input value={email} onChange={(e) => setEmail(e.target.value)} type="email" required />
      </label>
      <br />
      <label>
        Passwort:
        <input value={password} onChange={(e) => setPassword(e.target.value)} type="password" required />
      </label>
      <br />
      <button type="submit">Einloggen</button>
      <p>
        Kein Account? <a href="/register">Hier registrieren</a>
      </p>
    </form>
  );
}