import icon from "../assets/react.svg";

export default function TopNav({ leftIcon, rightIcon }) {
    return (
        <div
            style={{
                position: "fixed",
                top: 0,
                left: 0,
                right: 0,
                padding: "15px 20px",
                backgroundColor: "black",
                color: "white",
                borderBottomLeftRadius: "50% 20%",
                borderBottomRightRadius: "50% 20%",
                display: "flex",
                justifyContent: "space-between",
                alignItems: "center",
                zIndex: 9999,
            }}
        >
            <div>{leftIcon}</div>
            <div style={{ display: "flex", alignItems: "center", gap: "10px" }}>
                <img src={icon} alt="Icon" width={40} />
                <div>
                    <h1 style={{ margin: 0, fontSize: "18px" }}>Stadt Herne</h1>
                    <p style={{ margin: 0, fontSize: "12px" }}>
                        Mit Gruen, Mit Wasser, Mittendrin
                    </p>
                </div>
            </div>
            <div>{rightIcon}</div>
        </div>
    );
}
