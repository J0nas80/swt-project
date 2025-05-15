import { useNavigate } from "react-router-dom";
import { FiArrowLeft, FiMoreVertical } from "react-icons/fi";

export default function ChatTopNav({ userName }) {
    const navigate = useNavigate();

    return (
        <div
            style={{
                display: "flex",
                alignItems: "center",
                justifyContent: "space-between",
                padding: "10px 16px",
                backgroundColor: "#fff",
                borderBottom: "1px solid #ddd",
                position: "fixed",
                top: 0,
                left: 0,
                right: 0,
                zIndex: 1000,
                height: "60px",
                background: "gray",
                borderBottomLeftRadius: "50% 20%",
                borderBottomRightRadius: "50% 20%",
            }}
        >
            <button
                onClick={() => navigate(-1)}
                style={{
                    background: "none",
                    border: "none",
                    fontSize: "20px",
                    cursor: "pointer",
                }}
            >
                <FiArrowLeft />
            </button>

            {/* User Name */}
            <h2 style={{ margin: 0, fontSize: "18px", flex: 1, textAlign: "center" }}>{userName}</h2>

            {/* Options Icon */}
            <button
                onClick={() => alert("Optionen geöffnet")}
                style={{
                    background: "none",
                    border: "none",
                    fontSize: "20px",
                    cursor: "pointer",
                }}
            >
                <FiMoreVertical />
            </button>
        </div>
    );
}
