import { useEffect, useState } from "react";
import { getTornei } from "../services/api";
import { Link } from "react-router-dom";

type Torneo = {
  id: number;
  nome: string;
  anno: number;
};

export default function TorneiPage() {
  const [tornei, setTornei] = useState<Torneo[]>([]);

  useEffect(() => {
    getTornei().then(setTornei);
  }, []);

  return (
    <div style={styles.page}>
      <h1 style={styles.title}>⚽ Tornei</h1>

      <div style={styles.grid}>
        {tornei.map(t => (
          <Link key={t.id} to={`/tornei/${t.id}`} style={styles.card}>
            <h2 style={styles.cardTitle}>{t.nome}</h2>
            <p style={styles.cardText}>Anno: {t.anno}</p>
          </Link>
        ))}
      </div>
    </div>
  );
}

const styles: { [key: string]: React.CSSProperties } = {
  page: {
    padding: "30px",
    fontFamily: "Arial, sans-serif",
    background: "#f4f6f8",
    minHeight: "100vh"
  },

  title: {
    textAlign: "center",
    marginBottom: "30px",
    fontSize: "32px"
  },

  grid: {
    display: "grid",
    gridTemplateColumns: "repeat(auto-fit, minmax(220px, 1fr))",
    gap: "20px"
  },

  card: {
    display: "block",
    padding: "20px",
    borderRadius: "12px",
    background: "white",
    textDecoration: "none",
    color: "black",
    boxShadow: "0 2px 10px rgba(0,0,0,0.1)",
    transition: "0.2s"
  },

  cardTitle: {
    margin: "0 0 10px 0"
  },

  cardText: {
    margin: 0,
    opacity: 0.7
  }
};