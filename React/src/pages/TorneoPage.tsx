import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import {
  getTorneoById,
  getCalendarioTorneo,
  getClassificaTorneo,
} from "../services/api";
import type { Torneo } from "../types/Torneo";

/* ─── design tokens ────────────────────────────────────────────────────────── */
const CSS = `
  @import url('https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Inter:wght@400;500;600;700&display=swap');

  :root {
    --pitch:   #0a3d1f;
    --grass:   #11562b;
    --lime:    #c8f135;
    --white:   #f5f7f0;
    --muted:   #8a9b80;
    --surface: #0e2a14;
    --card:    #122e18;
    --border:  #1f4726;
    --gold:    #f5c842;
  }

  .tp-wrap {
    font-family: 'Inter', sans-serif;
    background: var(--pitch);
    color: var(--white);
    min-height: 100vh;
    padding-bottom: 60px;
  }

  /* ── hero ── */
  .tp-hero {
    background: var(--grass);
    background-image:
      repeating-linear-gradient(
        90deg,
        rgba(255,255,255,.03) 0px,
        rgba(255,255,255,.03) 1px,
        transparent 1px,
        transparent 48px
      ),
      repeating-linear-gradient(
        0deg,
        rgba(255,255,255,.03) 0px,
        rgba(255,255,255,.03) 1px,
        transparent 1px,
        transparent 48px
      );
    padding: 48px 32px 36px;
    border-bottom: 2px solid var(--lime);
    position: relative;
    overflow: hidden;
  }

  .tp-hero::after {
    content: "⚽";
    position: absolute;
    right: -10px;
    top: 50%;
    transform: translateY(-50%);
    font-size: 180px;
    opacity: 0.06;
    pointer-events: none;
    line-height: 1;
  }

  .tp-back {
    display: inline-flex;
    align-items: center;
    gap: 6px;
    font-size: 12px;
    font-weight: 600;
    letter-spacing: .12em;
    text-transform: uppercase;
    color: var(--lime);
    text-decoration: none;
    margin-bottom: 20px;
    opacity: .8;
    transition: opacity .15s;
  }
  .tp-back:hover { opacity: 1; }

  .tp-badge {
    display: inline-block;
    background: var(--lime);
    color: var(--pitch);
    font-size: 11px;
    font-weight: 700;
    letter-spacing: .12em;
    text-transform: uppercase;
    padding: 3px 10px;
    border-radius: 2px;
    margin-bottom: 10px;
  }

  .tp-title {
    font-family: 'Bebas Neue', sans-serif;
    font-size: clamp(40px, 8vw, 72px);
    letter-spacing: .03em;
    line-height: .95;
    margin: 0 0 12px;
    color: var(--white);
  }

  .tp-desc {
    font-size: 14px;
    color: var(--muted);
    max-width: 520px;
    line-height: 1.6;
    margin: 0;
  }

  /* ── layout ── */
  .tp-body {
    padding: 36px 32px 0;
    max-width: 960px;
    margin: 0 auto;
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 28px;
  }

  @media (max-width: 660px) {
    .tp-body { grid-template-columns: 1fr; padding: 24px 16px 0; }
    .tp-hero { padding: 32px 16px 24px; }
  }

  .tp-full { grid-column: 1 / -1; }

  /* ── section card ── */
  .tp-card {
    background: var(--card);
    border: 1px solid var(--border);
    border-radius: 8px;
    overflow: hidden;
  }

  .tp-card-head {
    display: flex;
    align-items: center;
    gap: 10px;
    padding: 14px 18px;
    border-bottom: 1px solid var(--border);
    background: var(--surface);
  }

  .tp-card-icon {
    width: 28px;
    height: 28px;
    border-radius: 6px;
    background: var(--lime);
    color: var(--pitch);
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 14px;
    flex-shrink: 0;
  }

  .tp-card-title {
    font-size: 11px;
    font-weight: 700;
    letter-spacing: .12em;
    text-transform: uppercase;
    color: var(--muted);
    margin: 0;
  }

  .tp-card-body { padding: 18px; }

  /* ── squadre ── */
  .tp-squad-list {
    list-style: none;
    margin: 0;
    padding: 0;
    display: flex;
    flex-direction: column;
    gap: 6px;
  }

  .tp-squad-item {
    display: flex;
    align-items: center;
    gap: 10px;
    padding: 8px 12px;
    border-radius: 6px;
    background: var(--surface);
    font-size: 14px;
    font-weight: 500;
    transition: background .15s;
  }
  .tp-squad-item:hover { background: var(--border); }
  .tp-squad-item a { cursor: pointer; }

  .tp-squad-num {
    font-size: 10px;
    font-weight: 700;
    color: var(--muted);
    width: 18px;
    text-align: right;
    flex-shrink: 0;
  }

  /* ── calendario ── */
  .tp-match {
    display: flex;
    flex-direction: column;
    gap: 6px;
    padding: 10px 12px;
    border-radius: 6px;
    background: var(--surface);
    margin-bottom: 6px;
    font-size: 13px;
    transition: background .15s;
    text-decoration: none;
    color: inherit;
  }
  .tp-match:hover { background: var(--border); cursor: pointer; }
  .tp-match:last-child { margin-bottom: 0; }

  .tp-match-date {
    font-size: 10px;
    font-weight: 600;
    letter-spacing: .08em;
    text-transform: uppercase;
    color: var(--muted);
  }

  .tp-match-row {
    display: flex;
    align-items: center;
    gap: 0;
  }

  .tp-match-team {
    flex: 1;
    font-weight: 600;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  .tp-match-team.right { text-align: right; }

  .tp-score {
    display: flex;
    align-items: center;
    gap: 4px;
    padding: 4px 10px;
    margin: 0 8px;
    border-radius: 4px;
    background: var(--pitch);
    border: 1px solid var(--border);
    font-family: 'Bebas Neue', sans-serif;
    font-size: 18px;
    letter-spacing: .06em;
    color: var(--lime);
    white-space: nowrap;
    flex-shrink: 0;
  }
  .tp-score-sep { color: var(--muted); font-size: 14px; }

  /* ── classifica ── */
  .tp-table {
    width: 100%;
    border-collapse: collapse;
    font-size: 13px;
  }

  .tp-table thead tr {
    border-bottom: 1px solid var(--border);
  }

  .tp-table th {
    padding: 6px 10px;
    text-align: left;
    font-size: 10px;
    font-weight: 700;
    letter-spacing: .1em;
    text-transform: uppercase;
    color: var(--muted);
  }
  .tp-table th.num { text-align: right; }

  .tp-table tbody tr {
    border-bottom: 1px solid var(--border);
    transition: background .12s;
  }
  .tp-table tbody tr:last-child { border-bottom: none; }
  .tp-table tbody tr:hover { background: var(--surface); }

  .tp-table td {
    padding: 10px 10px;
    vertical-align: middle;
  }
  .tp-table td.num {
    text-align: right;
    font-variant-numeric: tabular-nums;
    color: var(--muted);
  }

  .tp-rank {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    width: 20px;
    height: 20px;
    border-radius: 4px;
    font-size: 11px;
    font-weight: 700;
    background: var(--border);
    color: var(--muted);
    margin-right: 8px;
    flex-shrink: 0;
  }
  .tp-rank.gold { background: var(--gold); color: #1a1000; }
  .tp-rank.silver { background: #b0b8b0; color: #111; }
  .tp-rank.bronze { background: #a05c30; color: #fff; }

  .tp-pts {
    font-family: 'Bebas Neue', sans-serif;
    font-size: 20px;
    color: var(--lime);
  }

  /* ── empty ── */
  .tp-empty {
    text-align: center;
    color: var(--muted);
    font-size: 13px;
    padding: 20px 0;
  }

  /* ── loading ── */
  .tp-loading {
    min-height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    background: var(--pitch);
    font-family: 'Bebas Neue', sans-serif;
    font-size: 32px;
    letter-spacing: .08em;
    color: var(--lime);
  }
`;

/* ─── helpers ───────────────────────────────────────────────────────────────── */
function rankClass(i: number) {
  if (i === 0) return "gold";
  if (i === 1) return "silver";
  if (i === 2) return "bronze";
  return "";
}

function formatDataOra(dataOra: string) {
  const data = new Date(dataOra);
  return Number.isNaN(data.getTime())
    ? dataOra
    : data.toLocaleString("it-IT", {
      day: "2-digit",
      month: "2-digit",
      year: "numeric",
      hour: "2-digit",
      minute: "2-digit",
    });
}

/* ─── component ─────────────────────────────────────────────────────────────── */
export default function TorneoPage() {
  const { id } = useParams();
  const [torneo, setTorneo] = useState<Torneo | null>(null);
  const [partite, setPartite] = useState<any[]>([]);
  const [classifica, setClassifica] = useState<any[]>([]);

  useEffect(() => {
    if (id) {
      const torneoId = Number(id);

      getTorneoById(torneoId).then((t) => {
        setTorneo(t);
        document.title = t.nome;
      });

      getCalendarioTorneo(torneoId).then(setPartite);
      getClassificaTorneo(torneoId).then(setClassifica);
    }
  }, [id]);

  if (!torneo)
    return (
      <>
        <style>{CSS}</style>
        <div className="tp-loading">Caricamento…</div>
      </>
    );

  const squadre = torneo.partecipazioni ?? [];

  return (
    <>
      <style>{CSS}</style>
      <div className="tp-wrap">

        {/* ── HERO ── */}
        <header className="tp-hero">
          <Link className="tp-back" to="/tornei">
            ← Tornei
          </Link>
          <div className="tp-badge">{torneo.anno}</div>
          <h1 className="tp-title">{torneo.nome}</h1>
          {torneo.descrizione && <p className="tp-desc">{torneo.descrizione}</p>}
        </header>

        {/* ── BODY ── */}
        <div className="tp-body">

          {/* classifica */}
          <section className="tp-card tp-full">
            <div className="tp-card-head">
              <span className="tp-card-icon">🏆</span>
              <h2 className="tp-card-title">Classifica</h2>
            </div>
            <div className="tp-card-body" style={{ padding: "0 0 4px" }}>
              {classifica.length > 0 ? (
                <table className="tp-table">
                  <thead>
                    <tr>
                      <th style={{ paddingLeft: 18 }}>Squadra</th>
                      <th className="num">Pt</th>
                      <th className="num">GF</th>
                      <th className="num" style={{ paddingRight: 18 }}>GS</th>
                    </tr>
                  </thead>
                  <tbody>
                    {classifica.map((c: any, i) => (
                      <tr key={i}>
                        <td style={{ paddingLeft: 18 }}>
                          <span className={`tp-rank ${rankClass(i)}`}>{i + 1}</span>
                          {c.squadra}
                        </td>
                        <td className="num">
                          <span className="tp-pts">{c.punti}</span>
                        </td>
                        <td className="num">{c.golFatti}</td>
                        <td className="num" style={{ paddingRight: 18 }}>{c.golSubiti}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              ) : (
                <p className="tp-empty">Classifica non disponibile</p>
              )}
            </div>
          </section>

          {/* squadre */}
          <section className="tp-card">
            <div className="tp-card-head">
              <span className="tp-card-icon">🛡</span>
              <h2 className="tp-card-title">Squadre iscritte</h2>
            </div>
            <div className="tp-card-body">
              {squadre.length > 0 ? (
                <ul className="tp-squad-list">
                  {squadre.map((p, i) => (
                    <li className="tp-squad-item" key={p.id}>
                      <span className="tp-squad-num">{i + 1}</span>
                      {p.squadra?.id ? (
                        <a
                          href={`http://localhost:8080/squadre/${p.squadra.id}`}
                          style={{ color: "inherit", textDecoration: "none", flex: 1 }}
                        >
                          {p.squadra?.nome ?? "—"}
                        </a>
                      ) : (
                        p.squadra?.nome ?? "—"
                      )}
                    </li>
                  ))}
                </ul>
              ) : (
                <p className="tp-empty">Nessuna squadra iscritta</p>
              )}
            </div>
          </section>

          {/* calendario */}
          <section className="tp-card">
            <div className="tp-card-head">
              <span className="tp-card-icon">📅</span>
              <h2 className="tp-card-title">Calendario partite</h2>
            </div>
            <div className="tp-card-body">
              {partite.length > 0 ? (
                partite.map((p, i) => (
                  <a
                    className="tp-match"
                    key={i}
                    href={`http://localhost:8080/commenti/${p.id}`}
                  >
                    <span className="tp-match-date">{formatDataOra(p.dataOra)}</span>
                    <span className="tp-match-row">
                      <span className="tp-match-team">{p.squadraCasa}</span>
                      <span className="tp-score">
                        {p.golCasa}
                        <span className="tp-score-sep">:</span>
                        {p.golOspite}
                      </span>
                      <span className="tp-match-team right">{p.squadraOspite}</span>
                    </span>
                  </a>
                ))
              ) : (
                <p className="tp-empty">Nessuna partita ancora</p>
              )}
            </div>
          </section>

        </div>
      </div>
    </>
  );
}