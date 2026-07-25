import { useEffect, useState } from "react";
import { getTornei } from "../services/api";
import { Link } from "react-router-dom";

import type { Torneo } from "../types/Torneo";

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

  .tl-wrap {
    font-family: 'Inter', sans-serif;
    background: var(--pitch);
    color: var(--white);
    min-height: 100vh;
    padding-bottom: 60px;
  }

  /* ── hero ── */
  .tl-hero {
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
    padding: 56px 32px 44px;
    border-bottom: 2px solid var(--lime);
    position: relative;
    overflow: hidden;
    text-align: center;
  }

  .tl-hero::after {
    content: "🏆";
    position: absolute;
    right: -10px;
    top: 50%;
    transform: translateY(-50%);
    font-size: 200px;
    opacity: 0.05;
    pointer-events: none;
    line-height: 1;
  }

  .tl-eyebrow {
    display: inline-block;
    background: var(--lime);
    color: var(--pitch);
    font-size: 11px;
    font-weight: 700;
    letter-spacing: .14em;
    text-transform: uppercase;
    padding: 3px 12px;
    border-radius: 2px;
    margin-bottom: 14px;
  }

  .tl-back {
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
  .tl-back:hover { opacity: 1; }

  .tl-title {
    font-family: 'Bebas Neue', sans-serif;
    font-size: clamp(52px, 10vw, 88px);
    letter-spacing: .04em;
    line-height: .92;
    margin: 0;
    color: var(--white);
  }

  /* ── grid ── */
  .tl-body {
    padding: 40px 32px 0;
    max-width: 1040px;
    margin: 0 auto;
  }

  @media (max-width: 600px) {
    .tl-body { padding: 24px 16px 0; }
    .tl-hero { padding: 36px 16px 28px; }
  }

  .tl-count {
    font-size: 11px;
    font-weight: 700;
    letter-spacing: .12em;
    text-transform: uppercase;
    color: var(--muted);
    margin-bottom: 20px;
  }

  .tl-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
    gap: 16px;
  }

  /* ── card ── */
  .tl-card {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    background: var(--card);
    border: 1px solid var(--border);
    border-radius: 8px;
    padding: 20px;
    text-decoration: none;
    color: var(--white);
    transition: border-color .15s, transform .15s;
    position: relative;
    overflow: hidden;
    min-height: 130px;
  }

  .tl-card::before {
    content: '';
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    height: 3px;
    background: var(--lime);
    transform: scaleX(0);
    transform-origin: left;
    transition: transform .2s ease;
  }

  .tl-card:hover {
    border-color: var(--lime);
    transform: translateY(-2px);
  }

  .tl-card:hover::before {
    transform: scaleX(1);
  }

  .tl-card-name {
    font-family: 'Bebas Neue', sans-serif;
    font-size: 26px;
    letter-spacing: .04em;
    line-height: 1.1;
    margin: 0 0 12px;
    color: var(--white);
  }

  .tl-card-footer {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .tl-card-anno {
    font-size: 12px;
    font-weight: 600;
    letter-spacing: .08em;
    color: var(--muted);
    text-transform: uppercase;
  }

  .tl-card-arrow {
    font-size: 18px;
    color: var(--lime);
    opacity: 0;
    transform: translateX(-4px);
    transition: opacity .15s, transform .15s;
  }

  .tl-card:hover .tl-card-arrow {
    opacity: 1;
    transform: translateX(0);
  }

  /* ── empty ── */
  .tl-empty {
    text-align: center;
    color: var(--muted);
    font-size: 14px;
    padding: 60px 0;
  }
`;

export default function TorneiPage() {
  const [tornei, setTornei] = useState<Torneo[]>([]);

  useEffect(() => {
    getTornei().then(setTornei);
  }, []);

  useEffect(() => {
    document.title = "Tornei";

    getTornei().then(setTornei);
  }, []);

  return (
    <>
      <style>{CSS}</style>
      <div className="tl-wrap">

        <header className="tl-hero">
          <div>
            <a href="http://localhost:8080/home" className="tl-back">← Home</a>
          </div>
          <div className="tl-eyebrow">Stagioni</div>
          <h1 className="tl-title">Tornei</h1>
        </header>

        <div className="tl-body">
          {tornei.length > 0 && (
            <p className="tl-count">{tornei.length} torneo{tornei.length !== 1 ? "i" : ""}</p>
          )}

          <div className="tl-grid">
            {tornei.length > 0 ? (
              tornei.map(t => (
                <Link key={t.id} to={`/tornei/${t.id}`} className="tl-card">
                  <h2 className="tl-card-name">{t.nome}</h2>
                  <div className="tl-card-footer">
                    <span className="tl-card-anno">{t.anno}</span>
                    <span className="tl-card-arrow">→</span>
                  </div>
                </Link>
              ))
            ) : (
              <p className="tl-empty">Nessun torneo disponibile</p>
            )}
          </div>
        </div>

      </div>
    </>
  );
}