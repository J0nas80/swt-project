import { useLocation, useNavigate } from "react-router-dom";

const pageTitles = {
    "/saved": "Gespeichert",
    "/in-progress": "Verlauf",
    "/filter": "Filter",
    "/inserat-creating": "Inserat Erstellen",
    "/inserat/new": "Inserat Erstellen",
    "/help-feedback": "Hilfe & Feedback",
    "/user-data": "Benutzerdaten",
    "/chat": "Chat",
};

const simplePages = ["/", "/login", "/register"];

export default function BottomNav() {
    const location = useLocation();
    const navigate = useNavigate();
    const path = location.pathname;

    const isSimple = simplePages.includes(path);
    const title = pageTitles[path] || "";

    return (
        <div
            style={{
                position: "fixed",
                bottom: 0,
                left: 0,
                width: "100%",
                height: "60px",
                backgroundColor: "black",
                color: "white",
                borderTopLeftRadius: "50% 20%",
                borderTopRightRadius: "50% 20%",
                display: "flex",
                justifyContent: isSimple ? "center" : "space-between",
                alignItems: "center",
                padding: "0 20px",
                fontSize: "18px",
                fontWeight: "bold",
                zIndex: 1000,
            }}
        >
            {!isSimple && (
                <button
                    onClick={() => navigate(-1)}
                    style={{
                        background: "none",
                        border: "none",
                        color: "white",
                        fontSize: "18px",
                        cursor: "pointer",
                    }}
                >
                    ←
                </button>
            )}
            <span>{isSimple ? "" : title}</span>
            {!isSimple && <div style={{ width: "24px" }}></div>}
        </div>
    );
}
