import { useState, useRef, useEffect } from "react";
import { useParams } from "react-router-dom";
import PageLayout from "../components/PageLayout";
import { FiMic, FiPaperclip, FiCamera } from "react-icons/fi";
import ChatTopNav from "../components/ChatTopNav";

const chatUsers = {
    1: "Peter Hagen",
    2: "Albert Zweistein",
};

export default function ChatDetail() {
    const { id } = useParams();
    const userName = chatUsers[id] || "Unbekannter Nutzer";

    const [messages, setMessages] = useState([
        { from: "bot", text: `Hallo ${userName}! Wie kann ich helfen?` },
    ]);
    const [input, setInput] = useState("");

    const fileInputRef = useRef(null);
    const cameraInputRef = useRef(null);

    const sendMessage = () => {
        if (input.trim() === "") return;
        setMessages([...messages, { from: "user", text: input }]);
        setInput("");
        setTimeout(() => {
            setMessages((prev) => [
                ...prev,
                { from: "bot", text: "Danke für Ihre Nachricht!" },
            ]);
        }, 500);
    };

    const handleFileChange = (e) => {
        const file = e.target.files[0];
        if (file) {
            alert(`Datei ausgewählt: ${file.name}`);
        }
    };

    const handleCameraChange = (e) => {
        const file = e.target.files[0];
        if (file) {
            alert(`Bild aufgenommen: ${file.name}`);
        }
    };

    return (
        <div>
            <ChatTopNav userName="ChatDetail" />

            <PageLayout
                title={userName}
                subtitle="Sprechen Sie mit unserem Support-Team"
                showBottomNav={false}
            >
                <div
                    style={{
                        border: "1px solid #ccc",
                        height: "300px",
                        overflowY: "auto",
                        padding: "10px",
                        margin: "10px 0",
                        background: "#fafafa",
                    }}
                >
                    {messages.map((msg, idx) => (
                        <div
                            key={idx}
                            style={{
                                textAlign: msg.from === "user" ? "right" : "left",
                                margin: "5px 0",
                            }}
                        >
                            <span
                                style={{
                                    display: "inline-block",
                                    background: msg.from === "user" ? "#daf" : "#eee",
                                    padding: "8px 12px",
                                    borderRadius: "12px",
                                    maxWidth: "70%",
                                }}
                            >
                                {msg.text}
                            </span>
                        </div>
                    ))}
                </div>

                <div
                    style={{
                        display: "flex",
                        alignItems: "center",
                        border: "1px solid #ccc",
                        borderRadius: "8px",
                        padding: "5px 10px",
                        marginTop: "10px",
                        background: "#fff",
                    }}
                >
                    <button onClick={() => alert("Sprachaufnahme starten")} style={iconStyle}>
                        <FiMic />
                    </button>

                    <button onClick={() => fileInputRef.current.click()} style={iconStyle}>
                        <FiPaperclip />
                    </button>
                    <input
                        type="file"
                        ref={fileInputRef}
                        style={{ display: "none" }}
                        onChange={handleFileChange}
                    />

                    <button onClick={() => cameraInputRef.current.click()} style={iconStyle}>
                        <FiCamera />
                    </button>
                    <input
                        type="file"
                        accept="image/*"
                        capture="environment"
                        ref={cameraInputRef}
                        style={{ display: "none" }}
                        onChange={handleCameraChange}
                    />

                    <input
                        type="text"
                        value={input}
                        onChange={(e) => setInput(e.target.value)}
                        placeholder="Nachricht eingeben..."
                        style={{
                            flex: 1,
                            padding: "8px",
                            border: "none",
                            outline: "none",
                        }}
                    />
                </div>

                <button onClick={sendMessage} style={{ padding: "8px 12px", marginTop: "10px" }}>
                    Senden
                </button>
            </PageLayout>
        </div>
    );
}

const iconStyle = {
    background: "none",
    border: "none",
    fontSize: "20px",
    cursor: "pointer",
    marginRight: "10px",
};
