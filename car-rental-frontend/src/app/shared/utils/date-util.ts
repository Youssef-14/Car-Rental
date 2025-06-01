export class DateUtils {
  static formatDate(date: Date): string {
    return date.toISOString().split('T')[0];
  }

  // Converts a date string in the format "YYYY-MM-DD" to a Date object
  // Example: "2023-10-05" becomes new Date(2023, 9, 5)
  static formatDateString(date: Date): string {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are zero-based
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  }

  static parseDate(dateString: string): Date {
    const parts = dateString.split('-');
    return new Date(Number(parts[0]), Number(parts[1]) - 1, Number(parts[2]));
  }

  static isValidDate(date: Date): boolean {
    return !isNaN(date.getTime());
  }
}
