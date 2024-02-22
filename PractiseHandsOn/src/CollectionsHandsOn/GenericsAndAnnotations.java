package CollectionsHandsOn;

public class GenericsAndAnnotations {

	public static void main(String[] args) {
	//generics- giving type to the classes or interfaces on what type they can store (In def-<T>)&(In decl-<Type>)
		//bounded generic- we are giving certain bound or limit on the type that is passed to the class (def - <T extends Type>) <-upperbound
		//generic method - all in def only (before method return type) and (in class after class name) -> <T>
		//generic methods - public <T,V> T method(T num1,V num2) { return num1;}
		//Generics even allows multiple generic types - <T,V>
	//wild cards - let a method -> public void printList(List<object> list){ sysout(list);} and in main() -> List<Integer> list = new ArrayList<>();//we get error
		//**becoz an Integer(every class in java) is a sub class of object but a list of Integers is not a subclass of a list of objects
		//wildcard -> <?> - we use it when we don't know what exactly the type of generic is going to be  --> printList(List<?> list) <-It says accepting a list of anything
		//wildcard with bounded - (List<? extends Animal> list) - so it accepts a list of any type which is a subclass of Animal
		//upperbound - <? extends Animal>        lowerbound- <? super Cat>
		//https://www.youtube.com/watch?v=K1iu1kXkVoA
		
		//Classname.getClass() present in Object class(Parent class) returns the class object which represents the runtime class of the object - Classname 
		//Classname.class - also represents the same [.class is used when there isn't an instance of the class available.]
		//Print myPrint = new Print();		
		//System.out.println(Print.class.getName());         //.getClass() is used when there is an instance of the class available.
		//System.out.println(myPrint.getClass().getName());	 //object.getClass() returns the class of the given object.
		
		
		
	//Annotations - They are meta data. They are supplemental info that we can put into the java code.
		//They don't directly affect the code we annotate but they are processed by at compile or run time.
		//Ex: @SuppressWarnings("unused" //<-must for this annotation//) - To get rid of the warning that we get at the run time when we doesn't use that variable in our entire code
		//custom annotation creation --------> 
	//FOR CLASS :
		//1.@Target(ElementType.Type)  <- To tell that this custom annotation can be used to annotate only classes
		//2.@Retention(RetentionPolicy.RUNTIME)  <- It tells to keep the annotation throughout running of the code[It gives the lifetime of the annotation]
		//3.public @interface VeryImportant{}    <- custom annotation declaration and definition
		//And we can use this to annotate any class -> @VeryImportant
		//**obj.getClass()  <-this gives the class of that particular object and we can call static methods from this
		//**if(obj.getClass.isAnnotationPresent(VeryImportant.class) return true;   <-The main intention is to go to the class and check if this ann is very important or not
	//FOR METHOD :
		//**for(Method method : obj.getClass().getDeclaredMethods() )  <-It returns an array of all the method objects of the class which we can iterate through the for loop
		//**method.invoke(obj) <- will invoke that particular method automatically
		//parameters -> we can declare a variable inside a cutome ann - > int times();  <-but in the form of func and use in at class ann -> @VeryImportant(times=3). If we give int times() default 1; then it is not mandatory to pass this parameter in custom ann
		//we can create an obj for custom ann in main() and access this variable
	//FOR FIELDS :
		//for(Field field:obj.getClass().getDeclaredFields())  <-To get all of the fields declared to that class
		//Object objectvalue = field.get(obj)    //we don't know what it is returning so we perform this way
		//if(objectvalue instanceof String stringvalue)  <-it says whether obj is an instance of string (Type casting) and if it is, it puts the field value into stringvalue
	
	
	
	
	
	
	
	}

}


