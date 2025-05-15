export default function PageLayout({ children, title, subtitle, showChatLink = true, showBottomNav = true }) {
    return (
        <div style={{ padding: "20px", paddingBottom: "80px", marginTop: "60px"}}>
            {title && <h1>{title}</h1>}
            {subtitle && <p style={{ color: "#888" }}>{subtitle}</p>}
            {children}
        </div>
    );
}
