public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository();
        repository.createTable();
        repository.insert();
        repository.query();
    }
}
