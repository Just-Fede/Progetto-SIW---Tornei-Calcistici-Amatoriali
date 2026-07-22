const BASE_URL = "http://localhost:8080/api";

export async function getTornei() {
  const res = await fetch(`${BASE_URL}/tornei`);
  return res.json();
}

export async function getTorneoById(id: number) {
  const res = await fetch(`${BASE_URL}/tornei/${id}`);
  return res.json();
}

export const getClassificaTorneo = (id: number) =>
  fetch(`http://localhost:8080/api/tornei/${id}/classifica`)
    .then(res => res.json());

export const getCalendarioTorneo = (id: number) =>
  fetch(`http://localhost:8080/api/tornei/${id}/calendario`)
    .then(res => res.json());