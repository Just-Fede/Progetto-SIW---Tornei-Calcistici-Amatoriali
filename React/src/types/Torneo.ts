export type Squadra = {
  id: number;
  nome: string;
};

export type Partecipazione = {
  id: number;
  squadra: Squadra;
};

export type Torneo = {
  id: number;
  nome: string;
  anno: number;
  descrizione: string;
  partecipazioni?: Partecipazione[];
};