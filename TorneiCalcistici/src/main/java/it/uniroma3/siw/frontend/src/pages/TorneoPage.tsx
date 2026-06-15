import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { getTorneoById } from "../services/api";
import type { Torneo } from "../types/Torneo";

export default function TorneoPage() {
  const { id } = useParams();
  const [torneo, setTorneo] = useState<Torneo | null>(null);

  useEffect(() => {
    if (id) {
      getTorneoById(Number(id)).then(setTorneo);
    }
  }, [id]);

  if (!torneo) return <p>Caricamento...</p>;

  return (
    <div style={{ padding: 20 }}>
      <h1>{torneo.nome}</h1>
      <p>Anno: {torneo.anno}</p>
      <p>{torneo.descrizione}</p>

      <Link to="/tornei">← Torna indietro</Link>
    </div>
  );
}