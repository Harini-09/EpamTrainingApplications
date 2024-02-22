package DipActivity;

public class TicketBookingApp {
	private Card card;

	public TicketBookingApp(Card card) {
		this.card = card;
	}

	public void doPayment(int noOfTickets, int amount) {
		card.doTransaction(amount);
	}

	public static void main(String[] args) {
		Card debitCard = new DebitCard();
		TicketBookingApp ticketApp = new TicketBookingApp(debitCard);
		ticketApp.doPayment(4, 5000);

		Card creditCard = new CreditCard();
		TicketBookingApp ticketApp2 = new TicketBookingApp(creditCard);
		ticketApp2.doPayment(4, 5000);

	}
}