package actions.enums;

import actions.impl.author.add_actions.AddAuthor;
import actions.impl.author.add_actions.AddChosenPublishersForAddedAuthor;
import actions.impl.author.add_actions.ChoosePublishersForAddedAuthor;
import actions.impl.author.delete_actions.DeleteAuthor;
import actions.impl.author.delete_actions.FindAuthorByIdToDelete;
import actions.impl.author.find_actions.*;
import actions.impl.author.update_actions.ChoosePublishersForUpdatedAuthor;
import actions.impl.author.update_actions.FindAuthorByIdToUpdate;
import actions.impl.author.update_actions.UpdateAuthor;
import actions.impl.author.update_actions.UpdateChosenPublishersForExistingAuthor;
import actions.impl.book.add_actions.*;
import actions.impl.book.delete_actions.DeleteBook;
import actions.impl.book.delete_actions.FindBookByIdToDelete;
import actions.impl.book.find_actions.FindAllBooks;
import actions.impl.book.find_actions.FindBookByName;
import actions.impl.book.find_actions.SortBooks;
import actions.impl.book.update_actions.*;
import actions.impl.genre.add_actions.AddGenre;
import actions.impl.genre.delete_actions.DeleteGenre;
import actions.impl.genre.find_actions.FindAllGenres;
import actions.impl.genre.find_actions.FindGenreById;
import actions.impl.genre.find_actions.FindGenreByName;
import actions.impl.genre.find_actions.SortGenres;
import actions.impl.genre.update_actions.UpdateGenre;
import actions.impl.publisher.add_actions.AddPublisher;
import actions.impl.publisher.delete_actions.DeletePublisher;
import actions.impl.publisher.find_actions.*;
import actions.impl.publisher.update_actions.UpdatePublisher;
import actions.impl.user.add_actions.AddUser;
import actions.impl.user.delete_actions.DeleteUser;
import actions.impl.user.delete_actions.DeleteUserByAdmin;
import actions.impl.user.find_actions.*;
import actions.impl.user.update_actions.UpdateUser;
import actions.interfaces.Command;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-28 14:45
 */
public enum CommandName {

    ADD_BOOK {
        {
            this.command = new AddBook();
        }
    },

    DELETE_BOOK {
        {
            this.command = new DeleteBook();
        }
    },

    UPDATE_BOOK {
        {
            this.command = new UpdateBook();
        }
    },

    FIND_BOOK_BY_ID_TO_UPDATE {
        {
            this.command = new FindBookByIdToUpdate();
        }
    },

    FIND_BOOK_BY_ID_TO_DELETE {
        {
            this.command = new FindBookByIdToDelete();
        }
    },

    FIND_BOOK_BY_NAME {
        {
            this.command = new FindBookByName();
        }
    },

    FIND_ALL_BOOKS {
        {
            this.command = new FindAllBooks();
        }
    },

    SORT_BOOKS {
        {
            this.command = new SortBooks();
        }
    },

    CHOOSE_AUTHORS_FOR_ADDED_BOOK {
        {
            this.command = new ChooseAuthorsForAddedBook();
        }
    },

    ADD_CHOSEN_AUTHORS_FOR_ADDED_BOOK {
        {
            this.command = new AddChosenAuthorsForAddedBook();
        }
    },

    CHOOSE_GENRES_FOR_ADDED_BOOK {
        {
            this.command = new ChooseGenresForAddedBook();
        }
    },

    ADD_CHOSEN_GENRES_FOR_ADDED_BOOK {
        {
            this.command = new AddChosenGenresForAddedBook();
        }
    },

    CHOOSE_PUBLISHER_FOR_ADDED_BOOK {
        {
            this.command = new ChoosePublisherForAddedBook();
        }
    },

    ADD_CHOSEN_PUBLISHER_FOR_ADDED_BOOK {
        {
            this.command = new AddChosenPublisherForAddedBook();
        }
    },

    CHOOSE_AUTHORS_FOR_EXISTING_BOOK {
        {
            this.command = new ChooseAuthorsForExistingBook();
        }
    },

    UPDATE_CHOSEN_AUTHORS_FOR_EXISTING_BOOK {
        {
            this.command = new UpdateChosenAuthorsForExistingBook();
        }
    },

    CHOOSE_GENRES_FOR_EXISTING_BOOK {
        {
            this.command = new ChooseGenresForExistingBook();
        }
    },

    UPDATE_CHOSEN_GENRES_FOR_EXISTING_BOOK {
        {
            this.command = new UpdateChosenGenresForExistingBook();
        }
    },

    CHOOSE_PUBLISHER_FOR_EXISTING_BOOK {
        {
            this.command = new ChoosePublisherForExistingBook();
        }
    },

    UPDATE_CHOSEN_PUBLISHER_FOR_EXISTING_BOOK {
        {
            this.command = new UpdateChosenPublisherForExistingBook();
        }
    },

    ADD_AUTHOR {
        {
            this.command = new AddAuthor();
        }
    },

    DELETE_AUTHOR {
        {
            this.command = new DeleteAuthor();
        }
    },

    UPDATE_AUTHOR {
        {
            this.command = new UpdateAuthor();
        }
    },

    FIND_AUTHOR_BY_ID_TO_UPDATE {
        {
            this.command = new FindAuthorByIdToUpdate();
        }
    },

    FIND_AUTHOR_BY_ID_TO_DELETE {
        {
            this.command = new FindAuthorByIdToDelete();
        }
    },

    FIND_AUTHOR_BY_NAME {
        {
            this.command = new FindAuthorByName();
        }
    },

    FIND_ALL_AUTHORS {
        {
            this.command = new FindAllAuthors();
        }
    },

    SORT_AUTHORS {
        {
            this.command = new SortAuthors();
        }
    },

    SHOW_AUTHOR_ALL_BOOKS {
        {
            this.command = new ShowAuthorAllBooks();
        }
    },

    SHOW_AUTHOR_ALL_PUBLISHERS {
        {
            this.command = new ShowAuthorAllPublishers();
        }
    },

    CHOOSE_PUBLISHERS_FOR_ADDED_AUTHOR {
        {
            this.command = new ChoosePublishersForAddedAuthor();
        }
    },

    ADD_CHOSEN_PUBLISHERS_FOR_ADDED_AUTHOR {
        {
            this.command = new AddChosenPublishersForAddedAuthor();
        }
    },

    CHOOSE_PUBLISHERS_FOR_UPDATED_AUTHOR {
        {
            this.command = new ChoosePublishersForUpdatedAuthor();
        }
    },

    UPDATE_CHOSEN_PUBLISHERS_FOR_EXISTING_AUTHOR {
        {
            this.command = new UpdateChosenPublishersForExistingAuthor();
        }
    },


    ADD_GENRE {
        {
            this.command = new AddGenre();
        }
    },

    DELETE_GENRE {
        {
            this.command = new DeleteGenre();
        }
    },

    UPDATE_GENRE {
        {
            this.command = new UpdateGenre();
        }
    },

    FIND_GENRE_BY_ID {
        {
            this.command = new FindGenreById();
        }
    },

    FIND_GENRE_BY_NAME {
        {
            this.command = new FindGenreByName();
        }
    },

    FIND_ALL_GENRES {
        {
            this.command = new FindAllGenres();
        }
    },

    SORT_GENRES {
        {
            this.command = new SortGenres();
        }
    },

    ADD_PUBLISHER {
        {
            this.command = new AddPublisher();
        }
    },

    DELETE_PUBLISHER {
        {
            this.command = new DeletePublisher();
        }
    },

    UPDATE_PUBLISHER {
        {
            this.command = new UpdatePublisher();
        }
    },

    FIND_PUBLISHER_BY_ID {
        {
            this.command = new FindPublisherById();
        }
    },

    FIND_PUBLISHER_BY_NAME {
        {
            this.command = new FindPublisherByName();
        }
    },

    FIND_ALL_PUBLISHERS {
        {
            this.command = new FindAllPublishers();
        }
    },

    SORT_PUBLISHERS {
        {
            this.command = new SortPublishers();
        }
    },

    SHOW_PUBLISHER_ALL_BOOKS {
        {
            this.command = new ShowPublisherAllBooks();
        }
    },

    SHOW_PUBLISHER_ALL_AUTHORS {
        {
            this.command = new ShowPublisherAllAuthors();
        }
    },

    SIGN_IN_USER {
        {
            this.command = new SignInUser();
        }
    },

    SIGN_OUT_USER {
        {
            this.command = new SignOutUser();
        }
    },

    ADD_USER {
        {
            this.command = new AddUser();
        }
    },

    UPDATE_USER {
        {
            this.command = new UpdateUser();
        }
    },

    DELETE_USER {
        {
            this.command = new DeleteUser();
        }
    },

    DELETE_USER_BY_ADMIN {
        {
            this.command = new DeleteUserByAdmin();
        }
    },

    FIND_ALL_USERS {
        {
            this.command = new FindAllUsers();
        }
    },

    FIND_USER_BY_NAME {
        {
            this.command = new FindUserByName();
        }
    },

    SORT_USERS {
        {
            this.command = new SortUsers();
        }
    };

    Command command;

    public Command getCurrentCommand() {
        return command;
    }

}
