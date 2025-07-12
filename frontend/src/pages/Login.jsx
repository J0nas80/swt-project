import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import TopNav from "../components/TopNav";
import BottomNav from "../components/BottomNav";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const response = await axios.post(`http://${process.env.BACKEND_HOST}:${process.env.BACKEND_PORT}/api/auth/login`, {
        email,
        password,
      });

      // Save token, navigate, etc.
      localStorage.setItem("token", response.data.token);
      navigate("/home");
    } catch (err) {
      setError("Login fehlgeschlagen");
    }
  };

  return (
    <div style={{minHeight: "100vh", backgroundColor: "#1c1c1c", margin: 0, padding: 0, width: "100vw"}}>
       <TopNav />

      <div style={containerStyle}>
        <div style={cardStyle}>
          <h1 style={headingStyle}>Login</h1>
          <p style={paragraphStyle}>
            Melde dich an, um fortzufahren
          </p>
          <input
            type="text"
            placeholder="E-Mail"
            style={inputStyle}
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <input
            type="password"
            placeholder="Passwort"
            style={inputStyle}
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />

          <button onClick={handleLogin} style={buttonStyle}>
            Login
          </button>

          {error && (
            <p style={{ color: "red", textAlign: "center", marginTop: "10px" }}>
              {error}
            </p>
          )}

          <p style={{ marginTop: "15px", textAlign: "center" }}>
            Kein account?{" "}
            <Link to="/register" style={{ color: "#007bff" }}>
              Erstelle ein Account hier
            </Link>
          </p>
        </div>
      </div>
      <BottomNav />
    </div>
  );
}

const containerStyle = {
  display: "flex",
  justifyContent: "center",
  alignItems: "start",
  paddingTop: "180px",
};

const cardStyle = {
  backgroundColor: "white",
  padding: "30px",
  borderRadius: "10px",
  boxShadow: "0 4px 12px rgba(0,0,0,0.2)",
  maxWidth: "400px",
  width: "100%",
};


const headingStyle = {
  textAlign: "center",
  marginBottom: "10px",
};


const paragraphStyle = {
  textAlign: "center",
  color: "#666",
  marginBottom: "20px",
};

const inputStyle = {
  display: "block",
  margin: "10px 0",
  padding: "10px",
  width: "100%",
  border: "1px solid #ccc",
  borderRadius: "50px",
  backgroundColor: "black",
  color: "white",
  outline: "none",
};

const buttonStyle = {
  width: "100%",
  padding: "10px",
  backgroundColor: "transparent",
  color: "black",
  border: "2px solid black",
  borderRadius: "50px",
  marginTop: "10px",
  cursor: "pointer",
};
