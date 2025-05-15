// components/ChatSidebar.js
import React from "react";
import { useNavigate } from "react-router-dom";

const chatUsers = [
    { id: 1, name: "Peter Hagen", preview: "hey, habe dein profil..." },
    { id: 2, name: "Albert Zweistein", preview: "nein am preis ist nichts..." },
];

export default function ChatSidebar({ isOpen, onClose }) {
    const navigate = useNavigate();

    if (!isOpen) return null; 

    return (
        <>
            <div
                onClick={onClose}
                style={{
                    position: "fixed",
                    top: 0,
                    left: 0,
                    height: "100%",
                    width: "100%",
                    backgroundColor: "rgba(0, 0, 0, 0.3)",
                    zIndex: 998,
                }}
            />

            <div
                style={{
                    position: "fixed",
                    top: 0,
                    left: 0,
                    height: "100%",
                    width: "300px",
                    background: "linear-gradient(to right, #111, #333)",
                    color: "white",
                    padding: "20px",
                    zIndex: 999,
                    overflowY: "auto",
                    marginTop: "50px"
                }}
            >
                <button
                    onClick={onClose}
                    style={{
                        float: "right",
                        fontSize: "24px",
                        background: "none",
                        color: "#fff",
                        border: "none",
                        cursor: "pointer",
                    }}
                >
                    →
                </button>

                {chatUsers.map((user) => (
                    <div
                        key={user.id}
                        onClick={() => {
                            navigate(`/chat/${user.id}`);
                            onClose();
                        }}
                        style={{
                            padding: "10px",
                            backgroundColor: "#222",
                            borderBottom: "1px solid #444",
                            cursor: "pointer",
                            marginTop: "10px",
                        }}
                    >
                        <strong>{user.name}</strong>
                        <p style={{ margin: 0, fontSize: "14px", color: "#ccc" }}>{user.preview}</p>
                    </div>
                ))}
            </div>
        </>
    );
}
