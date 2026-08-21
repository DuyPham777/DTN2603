import java.time.LocalDate;

public class Question {
    private int questionId;
    private String content;
    private CategoryQuestion categoryQuestion;
    private TypeQuestion typeQuestion;
    private  Account creator;
    private LocalDate createDate;

    public Question(int questionId, String content, TypeQuestion typeQuestion, CategoryQuestion categoryQuestion, Account creator, LocalDate createDate) {
        this.questionId = questionId;
        this.content = content;
        this.typeQuestion = typeQuestion;
        this.categoryQuestion = categoryQuestion;
        this.creator = creator;
        this.createDate = createDate;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CategoryQuestion getCategoryQuestion() {
        return categoryQuestion;
    }

    public void setCategoryQuestion(CategoryQuestion categoryQuestion) {
        this.categoryQuestion = categoryQuestion;
    }

    public Account getCreator() {
        return creator;
    }

    public void setCreator(Account creator) {
        this.creator = creator;
    }

    public TypeQuestion getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(TypeQuestion typeQuestion) {
        this.typeQuestion = typeQuestion;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", content='" + content + '\'' +
                ", categoryQuestion=" + categoryQuestion +
                ", typeQuestion=" + typeQuestion +
                ", creator=" + creator +
                ", createDate=" + createDate +
                '}';
    }
}
