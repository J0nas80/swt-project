import { useState } from "react";
import { Link } from "react-router-dom";

export default function Sidebar() {
    const [open, setOpen] = useState(false);

    return (
        <>
            <button
                onClick={() => setOpen(!open)}
                style={{
                    background: "none",
                    border: "none",
                    color: "white",
                    fontSize: "22px",
                    cursor: "pointer",
                    position: "fixed",
                    top: "20px",
                    right: "20px",
                    zIndex: 10001,
                }}
            >
                ☰
            </button>

            {open && (
                <div
                    style={{
                        position: "fixed",
                        top: 70,
                        right: 0,
                        bottom: 0,
                        background: "#333",
                        padding: "20px 30px",
                        width: "250px",
                        boxShadow: "2px 0 5px rgba(0,0,0,0.2)",
                        zIndex: 9998,
                        transition: "transform 0.3s ease-in-out",
                        transform: open ? "translateX(0)" : "translateX(100%)",
                        display: "flex",
                        flexDirection: "column",
                    }}
                >
                    <ul style={{ listStyle: "none", padding: 0, margin: 0 }}>
                        {[
                            { to: "/saved", text: "Gespeichert" },
                            { to: "/in-progress", text: "Verlauf" },
                            { to: "/filter", text: "Filter" },
                            { to: "/inserat-creating", text: "Inserat erstellen" },
                            { to: "/help-feedback", text: "Hilfe und Feedback" },
                            { to: "/user-data", text: "User Data" },
                        ].map((item) => (
                            <li key={item.to} style={{ marginBottom: "20px" }}>
                                <Link to={item.to} style={linkStyle}>
                                    {item.text}
                                </Link>
                                <div style={underlineStyle}></div>
                            </li>
                        ))}
                    </ul>

                    <div style={{ marginTop: "auto", width: "100%" }}>
                        
                        <div
                            style={{
                                width: "calc(100% + 60px)",   
                                height: "2px",
                                backgroundColor: "white",
                                margin: "20px -30px",
                            }}
                        ></div>

                        <div style={{ display: "flex", alignItems: "center", marginBottom: "20px" }}>
                            <div
                                style={{
                                    width: "40px",
                                    height: "40px",
                                    borderRadius: "50%",
                                    backgroundColor: "#fff",
                                    display: "flex",
                                    justifyContent: "center",
                                    alignItems: "center",
                                    marginRight: "10px",
                                }}
                            >
                                <span style={{ fontSize: "20px" }}>👤</span>
                            </div>
                            <span style={{ color: "white", fontSize: "22px", fontWeight: "bold" }}>
                                Username
                            </span>
                        </div>
                    </div>
                </div>
            )}
        </>
    );
}


const linkStyle = {
    color: "white",
    fontSize: "22px",
    fontWeight: "bold",
    display: "block",
    textDecoration: "none",
    transition: "color 0.3s ease-in-out",
};

const underlineStyle = {
    width: "calc(100% + 60px)",       
    height: "2px",
    backgroundColor: "white",
    marginTop: "10px",
    marginLeft: "-30px",
    marginRight: "-30px",
};
