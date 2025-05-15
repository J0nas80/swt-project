import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import TopNav from "../components/TopNav";
import BottomNav from "../components/BottomNav";

export default function Register() {
  const navigate = useNavigate();
  const [visibleInputs, setVisibleInputs] = useState({});

  const togglePasswordVisibility = (id) => {
    setVisibleInputs((prev) => ({
      ...prev,
      [id]: !prev[id],
    }));
  };

  const fields = [
    "Vorname", "Nachname", "Geburtdatum", "Geschlecht",
    "E-Mail", "Telefon", "Passwort", "Passwort bestätigen"
  ];

  return (
    <div style={{minHeight: "100vh", color: "white", paddingBottom: "100px" }}>
      <TopNav />

      <div style={cardStyle}>
        <h1 style={{ textAlign: "center" }}>Erstelle einen neuen Account</h1>
        <p style={{ marginTop: "15px", textAlign: "center" }}>
          Bereits registriert?{" "}
          <Link to="/login" style={{ color: "#007bff" }}>Log in</Link>
        </p>

        {fields.map((field, idx) => {
          const isPassword = field.toLowerCase().includes("passwort");
          const isDate = field.toLowerCase().includes("geburtdatum");
          const inputId = `input-${idx}`;
          const inputType = isPassword
            ? visibleInputs[inputId] ? "text" : "password"
            : isDate
              ? "date"
              : "text";

          return (
            <div key={idx} style={{ position: "relative", margin: "10px 0" }}>
              <input
                id={inputId}
                type={inputType}
                placeholder={field}
                style={{
                  ...inputStyle,
                  // paddingRight: isPassword ? "40px" : "10px",
                }}
                required
              />
              {isPassword && (
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

        <button onClick={() => navigate("/login")} style={buttonStyle}>
          Registrieren
        </button>
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
