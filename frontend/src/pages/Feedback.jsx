import { useState } from "react";
import TopNav from "../components/TopNav";
import BottomNav from "../components/BottomNav";
import PageLayout from "../components/PageLayout";

export default function HelpFeedback() {
  const [message, setMessage] = useState("");

  const handleSend = () => {
    if (message.trim() === "") return;
    alert("Danke für Ihr Feedback!");
    setMessage("");
  };

  return (
    <div>
      <TopNav title="Hilfe & Feedback" />
      <PageLayout
        title="Hilfe & Feedback"
        subtitle="Schreiben Sie uns Ihre Anliegen oder Anregungen."
      >
        <div style={{ marginTop: "20px" }}>
          <textarea
            value={message}
            onChange={(e) => setMessage(e.target.value)}
            placeholder="Geben Sie Ihre Nachricht hier ein..."
            style={{
              width: "100%",
              height: "200px",
              padding: "12px",
              fontSize: "16px",
              borderRadius: "8px",
              border: "1px solid #ccc",
              resize: "none",
              boxSizing: "border-box",
            }}
          />
          <button
            onClick={handleSend}
            style={{
              marginTop: "20px",
              padding: "12px 20px",
              fontSize: "16px",
              backgroundColor: "black",
              color: "#fff",
              border: "none",
              borderRadius: "50px",
              cursor: "pointer",
              width: "100%",
            }}
          >
            Senden
          </button>
        </div>
      </PageLayout>
      <BottomNav title="Feedback" />
    </div>
  );
}
