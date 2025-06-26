import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import TopNav from "../components/TopNav";
import BottomNav from "../components/BottomNav";
import axios from "axios";

export default function Register() {
  const navigate = useNavigate();
  const [visibleInputs, setVisibleInputs] = useState({});
  const [formData, setFormData] = useState({
    vorname: "",
    nachname: "",
    geburtdatum: "",
    geschlecht: "",
    email: "",
    telefon: "",
    passwort: "",
    passwortBestaetigen: "",
  });
  const [error, setError] = useState("");

  const togglePasswordVisibility = (id) => {
    setVisibleInputs((prev) => ({
      ...prev,
      [id]: !prev[id],
    }));
  };
  const handleChange = (e) => {
    setFormData((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  const handleRegister = async () => {
    // Simple password match check
    if (formData.passwort !== formData.passwortBestaetigen) {
      setError("Passwörter stimmen nicht überein");
      return;
    }

    try {
      await axios.post("http://localhost:8080/api/register", {
        vorname: formData.vorname,
        nachname: formData.nachname,
        geburtdatum: formData.geburtdatum,
        geschlecht: formData.geschlecht,
        email: formData.email,
        telefon: formData.telefon,
        passwort: formData.passwort,
      });

      // On success: navigate to login
      navigate("/login");
    } catch (err) {
      setError("Registrierung fehlgeschlagen");
    }
  };

  return (
    <div style={{ minHeight: "100vh", color: "white", paddingBottom: "100px" }}>
      <TopNav />

      <div style={cardStyle}>
        <h1 style={{ textAlign: "center" }}>Erstelle einen neuen Account</h1>
        <p style={{ marginTop: "15px", textAlign: "center" }}>
          Bereits registriert?{" "}
          <Link to="/login" style={{ color: "#007bff" }}>Log in</Link>
        </p>

        {[
          { label: "Vorname", name: "vorname" },
          { label: "Nachname", name: "nachname" },
          { label: "Geburtdatum", name: "geburtdatum", type: "date" },
          { label: "Geschlecht", name: "geschlecht" },
          { label: "E-Mail", name: "email" },
          { label: "Telefon", name: "telefon" },
          { label: "Passwort", name: "passwort", type: "password" },
          { label: "Passwort bestätigen", name: "passwortBestaetigen", type: "password" },
        ].map((field, idx) => {
          const inputId = `input-${idx}`;
          const type = field.type === "password"
            ? visibleInputs[inputId] ? "text" : "password"
            : field.type || "text";

          return (
            <div key={idx} style={{ position: "relative", margin: "10px 0" }}>
              <input
                id={inputId}
                name={field.name}
                type={type}
                placeholder={field.label}
                value={formData[field.name]}
                onChange={handleChange}
                style={inputStyle}
                required
              />
              {field.type === "password" && (
                <span
                  onClick={() => togglePasswordVisibility(inputId)}
                  style={eyeIconStyle}
                >
                  👁️
                </span>
              )}
            </div>
          );
        })}

        <button onClick={handleRegister} style={buttonStyle}>
          Registrieren
        </button>

        {error && (
          <p style={{ color: "red", textAlign: "center", marginTop: "10px" }}>
            {error}
          </p>
        )}
      </div>

      <BottomNav />
    </div>
  );
}

const cardStyle = {
  backgroundColor: "white",
  color: "black",
  borderRadius: "4px",
  padding: "30px 20px", // add side padding
  maxWidth: "500px",
  margin: "50px auto",
  boxShadow: "0 4px 12px rgba(0,0,0,0.2)",
};



const inputStyle = {
  display: "block",
  margin: "10px 0",
  padding: "10px 20px",
  width: "100%",
  border: "1px solid #ccc",
  borderRadius: "50px",
  outline: "none",
  boxSizing: "border-box",
};



const buttonStyle = {
  width: "100%",
  padding: "10px",
  background: "black",
  color: "#fff",
  border: "none",
  borderRadius: "50px",
  marginTop: "10px",
};

const eyeIconStyle = {
  position: "absolute",
  right: "15px",
  top: "50%",
  transform: "translateY(-50%)",
  cursor: "pointer",
  fontSize: "18px",
  color: "#999",
};
