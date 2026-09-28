export function getToken(): string | null {
  const token = localStorage.getItem("Token");
  return token;
}

export function removeToken() {
  localStorage.removeItem("Token");
  localStorage.removeItem("Expiration");
}

export function isTokenExpired(): boolean {
  const expirationString = localStorage.getItem("Expiration");

  if (!expirationString) {
    return true;
  }
  const expirationTimestamp = parseInt(expirationString, 10);
  return !Number.isFinite(expirationTimestamp) || Date.now() >= expirationTimestamp;
}

// export function getUserID(): string | null {
//     return localStorage.getItem("UnitID")
// }

