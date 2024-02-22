
public class DummyStore {
	/* Question service junit test cases ->
	 * CrudOperationsOnQuestions operations; QuestionsLibrary questions;
	 * EntityManagerFactory eFactory =
	 * Persistence.createEntityManagerFactory("my-local-quiz-db"); EntityManager
	 * entityManager = eFactory.createEntityManager();
	 * 
	 * @BeforeEach void getObject() { operations = new CrudOperationsOnQuestions();
	 * }
	 * 
	 * @Test void testisQuestionIdPresentSuccessCase() throws
	 * InvalidQuestionIdEntryException { operations.isQuestionIdPresent("Q01");
	 * assertEquals(true,true); }
	 * 
	 * @Test void testisQuestionIdPresentFailedCase() { try {
	 * operations.isQuestionIdPresent("Q10"); }catch(Exception e){
	 * assertEquals("Warning!! The given Question id is not present in the library. Please enter a valid question id."
	 * ,e.getMessage()); } }
	 * 
	 * @Test void testInsert1SuccessCase() { Question newQuestion = new
	 * Question("Q04", "Arrays",
	 * "Which method is used to find the length of an array?",
	 * Arrays.asList("a.len", "b.size", "c.length", "d.none"), "Easy", "Java", "c");
	 * entityManager.getTransaction().begin(); entityManager.persist(newQuestion);
	 * entityManager.getTransaction().commit(); boolean result =
	 * operations.insert("Q04", newQuestion); assertEquals(true, result); }
	 * 
	 * @Test void testInsert2FailedCase() { boolean result =
	 * operations.insert("Q06", null); assertEquals(false, result); }
	 * 
	 * @Test void testDelete() { boolean result = operations.delete("Q01");
	 * assertEquals(false, result); }
	 * 
	 * @Test void testModifyTitle() { String result = operations.modifyTitle("Q01",
	 * "StreamsApi"); assertEquals("StreamsApi", result); }
	 * 
	 * @Test void testModifyQuestion() { String result =
	 * operations.modifyQuestion("Q03", "Which collections stores unique values");
	 * assertEquals("Which collections stores unique values", result); }
	 * 
	 * @Test void testModifyOptions() { List<String> options =
	 * Arrays.asList("a.list", "b.set", "c.map"); List<String> result =
	 * operations.modifyOptions("Q01", options); assertEquals(options, result); }
	 * 
	 * @Test void testModifyQuestionlevel() { String result =
	 * operations.modifyQuestionLevel("Q03", "Hard"); assertEquals("Hard", result);
	 * }
	 * 
	 * @Test void testModifyTopicTag() { String result =
	 * operations.modifyTopicTag("Q05", "Strings"); assertEquals("Strings", result);
	 * }
	 * 
	 * @Test void testModifyAnswer() { String result =
	 * operations.modifyAnswer("Q04", "d"); assertEquals("d", result); }
	 * 
	 * @Test void testView() { entityManager.getTransaction().begin();
	 * 
	 * @SuppressWarnings("unchecked") List<Question> list =
	 * entityManager.createQuery("from Question").getResultList();
	 * entityManager.getTransaction().commit(); List<Question> result =
	 * operations.viewQuestions(); assertEquals(list, result); }
	 * 
	 * }
	 */
	
	
	
	
	
	
	
	
	
	//Quiz service junit test cases ->
//	@BeforeEach
//	void getObjects() {
//		operations = new CrudOperationsOnQuiz();
//	}
//
//	@Test
//	void testIsQuestionIdPresentSuccessCase() throws InvalidQuestionIdEntryException {
//		operations.isQuestionIdPresent("Q01");
//		boolean isQuestionPresent=false;
//		entityManager.getTransaction().begin();
//		List<Question> questions = entityManager.createQuery("from Question").getResultList();
//		for (Question question : questions) {
//			if (question.getQuestionId().equals("Q01")) {
//				isQuestionPresent=true;
//			}
//		}
//		entityManager.getTransaction().commit();
//		assertEquals(true, isQuestionPresent);
//
//	}
//
//	@Test
//	void testIsQuestionIdPresentFailedCase() {
//		try {
//			operations.isQuestionIdPresent("Q11");
//		} catch (InvalidQuestionIdEntryException e) {
//			assertEquals("Warning!! The given Question id is not present in the library. Please enter a valid question id.",e.getMessage());
//		}
//	}
//
//	@Test
//	void testIsQuizTitlePresentSuccessCase() throws InvalidQuizTitleEntryException {
//		entityManager.getTransaction().begin();
//		Quiz quiz = entityManager.find(Quiz.class, 101);
//		operations.isQuizTitlePresent(101);
//		entityManager.getTransaction().commit();
//		assertEquals(true,quiz.equals(null) );
//	}
//
//	@Test
//	void testIsQuizTitlePresentFailedCase() {
//		try {
//			operations.isQuizTitlePresent(110);
//		} catch (InvalidQuizTitleEntryException e) {
//			assertEquals("Warning!! The entered Quiz Title is not present in the library. Please enter a valid Quiz Title.",e.getMessage());
//		}
//
//	}
//
//	@Test
//	void testIsQuestionInQuizPresentSuccessCase() throws NoSuchQuestionExistInQuizException {
//		boolean isQuestionPresent=false;
//		entityManager.getTransaction().begin();
//		Quiz quiz = entityManager.find(Quiz.class, 101);
//		entityManager.getTransaction().commit();
//		operations.isQuestionInQuizPresent(101,"Q01");
//		List<Question> questions = quiz.getQuestions();
//		for (Question question : questions) {
//			if (question.getQuestionId().equals("Q01")) {
//				isQuestionPresent=true;
//			}
//		}
//		assertEquals(true, isQuestionPresent);
//	}
//
//	@Test
//	void testIsQuestionInQuizPresentFailedCase() {
//		try {
//			operations.isQuestionInQuizPresent(101, "Q10");
//		} catch (NoSuchQuestionExistInQuizException e) {
//			assertEquals("Warning!! The entered Question Id is not present in the given Quiz. Please enter a valid Question Id present in the quiz.",e.getMessage());
//		}
//	}
//	
//	@Test
//	void addNewQuestionTest() {
//		Question newQuestion = operations.addNewQuestion("Q05");
//		assertEquals(true,newQuestion.getQuestionId().equals("Q05"));
//	}
//
//	@Test
//	void testInsertQuiz() {
//		List<Question> questions = getQuestions();
//		Quiz quiz = new Quiz(102,"Hard",questions,20);
//		assertEquals(true,operations.insert(102, quiz));
//	}
//
//	@Test
//	void testInsertNull() {
//		boolean result = operations.insert(103, null);
//		assertEquals(false, result);
//	}
//
//	@Test
//	void testDelete() {
//		boolean result = operations.delete(102);
//		assertEquals(false, result);
//	}
//
//	@Test
//	void testView() {
//		List<Quiz> result = operations.view();
//		assertEquals(entityManager.createQuery("from Quiz").getResultList(), result);
//	}
//
//	@Test
//	void testViewNull() {
//		List<Quiz> result = null;
//		assertEquals(null, result);
//	}
//
//	@Test
//	void testAddQuestion() {
//		boolean result = operations.addQuestion(101,"Q01");
//		assertEquals(true, result);
//	}
//
//	@Test
//	void testRemoveQuestion() {
//		boolean result = operations.removeQuestion(101, "Q02");
//		assertEquals(false, result);
//	}
// 
//	@Test
//	void testUpdateMarks() {
//		int result = operations.updateMarks(101,30);
//		assertEquals(30, result);
//	}
//	
//	List<Question> getQuestions() {
//		List<Question> questionsList = new ArrayList<>();
//		Question question = new Question("Q01", "Collections", "Which collection stores unique values?",
//				Arrays.asList("a.List", "b.Map", "c.Set", "d.Stack"), "Intermediate", "Java", "c");
//		question.setSno(1);
//		questionsList.add(question);
//		question = new Question("Q02", "Strings", "Which method is used to get the length of a string?",
//				Arrays.asList("a.length()", "b.size()", "c.len()", "d.none"), "Easy", "Java", "a");
//		question.setSno(2);
//		questionsList.add(question);
//		return questionsList;
//	}
//
//}
	
	
	
	
	
	

	//User service - junit test case
//	@BeforeEach
//	void getObject() {
//		authentication=new UserAuthenticationImpl() ;
//	}
//	
//	@Test
//	void testExistingAdminLogin() {
//		boolean result = authentication.logIn("miketandon", "mike#112","admin");
//		assertEquals(true,result);
//	}
//	
//	@Test
//	void testNotExistingAdminLogin() {
//		boolean result = authentication.logIn("kale", "kale#123","admin");
//		assertEquals(false,result);
//	}
//	
//	@Test
//	void testExistingUserLogin() {
//		boolean result = authentication.logIn("Terarus", "tery#112","user");
//		assertEquals(true,result);
//	}
//	
//	@Test
//	void testNotExistingUserLogin() {
//		boolean result = authentication.logIn("johnwilliams", "john@112","user");
//		assertEquals(false,result);
//	}
//	
//	@Test
//	void testAdminSignUp() {
//		boolean result = authentication.signUp("miketandon", "mike#112");
//		assertEquals(true,result);
//	}
//	
//	@Test
//	void testEmptyAdminSignUp() {
//		boolean result = authentication.signUp("", "");
//		assertEquals(false,result);
//	}
//	
//	@Test
//	void testAdminSignUpWithUserIdEmpty() {
//		boolean result = authentication.signUp("", "mike#112");
//		assertEquals(false,result);
//	}
//	
//	@Test
//	void testAdminSignUpWithPasswordEmpty() {
//		boolean result = authentication.signUp("", "mike#112");
//		assertEquals(false,result);
//	}


}
