package entity.Book;

public enum BookType {
    Fantasy(1),
    Science_Fiction(2),
    Dystopian(3),
    Action(4),
    Adventure(5),
    Mystery(6),
    Horror(7),
    Thriller(8),
    Historical_Fiction(9),
    Romance(10),
    Contemporary_Fiction(11),
    Young_Adult(12),
    Children(13),
    Memoir(14),
    Autobiography(15),
    Biography(16),
    Art(17),
    Photography(18),
    History(19),
    Travel(20),
    True_Crime(21),
    Essays(22),
    ArtBooks(23);

    private Integer id;

    BookType(int i) {
        id = i;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}