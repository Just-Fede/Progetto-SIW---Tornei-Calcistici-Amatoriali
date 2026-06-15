const BASE_URL = "http://localhost:8080/api";

export async function getTornei() {
  const res = await fetch(`${BASE_URL}/tornei`);
  return res.json();
}

export async function getTorneoById(id: number) {
  const res = await fetch(`${BASE_URL}/tornei/${id}`);
  return res.json();
}

export async function getClassifica(id: number) {
  const res = await fetch(`${BASE_URL}/tornei/${id}/classifica`);
  return res.json();
}

export async function getCalendario(id: number) {
  const res = await fetch(`${BASE_URL}/tornei/${id}/calendario`);
  return res.json();
}