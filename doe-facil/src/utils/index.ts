import moment from "moment";

export function removeNonNumericChars(value: string) {
  return value.replace(/[^\d]|-/g, "");
}

export function removeNumericAndSpecialChars(value: string) {
  return value.replace(/[^\p{L}\p{M}\s]|_/gu, "");
}

// Function to get the first day of the current month
export function getFirstDayOfCurrentMonth() {
  const now = new Date();
  return new Date(now.getFullYear(), now.getMonth(), 1);
}

// export Function to get today's date
export function getToday() {
  return new Date();
}

// formatar número de telefone para (XX) XXXX-XXXX
export function formatPhone(value: string) {
  return value.replace(/(\d{2})(\d{4})(\d{4})/, "($1) $2-$3");
}

export function UpperFirstLetter(nome: string): string {
  const preposicoes = ["do", "da", "dos", "das", "de"];

  return nome
    .split(" ")
    .map((word, index) => {
      // Mantém preposições minúsculas se não forem a primeira palavra
      if (preposicoes.includes(word) && index !== 0) {
        return word;
      }
      return word.charAt(0).toUpperCase() + word.slice(1);
    })
    .join(" ");
}

export function formatarCPF(cpf: any) {
  cpf = cpf.replace(/[^\d]/g, "");
  return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4");
}

export function calcularIdade(dataNascimento: any) {
  const hoje = moment();
  const nascimento = moment(dataNascimento, "YYYY-MM-DD");
  return hoje.diff(nascimento, "years");
}

export const tituloProjeto = "Doar Fácil"