import type { useToast } from "primevue";

// *** Caso o back-end seja em Laravel ***
// *** Exemplo de uso: toastSuccess(toast, response) | toastErro(toast, error) ***

// 'ReturnType<typeof useToast>' retorna o tipo do useToast (".add: (options: ToastOptions) => void")
// Essas funções recebe um objeto que tem o método add(), exatamente como o useToast() fornece
export const toastErro = (toast: ReturnType<typeof useToast>, error: any) => {
  const responseData = error?.response?.data;
  const errors = responseData?.errors;

  // Verifica se 'errors' existe e é um objeto válido (não null, nem array, nem outro tipo)
  if (errors && typeof errors === "object") {
    // Percorre todas as entradas (chave: campo, valor: lista de mensagens) | (precisa passar os dois parâmetros)
    Object.entries(errors).forEach(([campo, mensagens]) => {
      // Garante que o valor seja um array de mensagens antes de iterar
      if (Array.isArray(mensagens)) {
        mensagens.forEach((mensagem: string) => {
          // Para cada mensagem, exibe um toast separado
          toast.add({
            severity: "warn",
            summary: "Atenção",
            detail: mensagem,
            life: 6000,
          });
        });
      }
    });
  } else {
    toast.add({
      severity: "warn",
      summary: "Atenção",
      detail: responseData?.message || "Erro inesperado",
      life: 6000,
    });
  }
};

export const toastSuccess = (
  toast: ReturnType<typeof useToast>,
  detail: string
) => {
  toast.add({
    severity: "success",
    summary: "Sucesso",
    detail: detail,
    life: 3000,
  });
};
