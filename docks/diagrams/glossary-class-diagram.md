### Содержание

1. [ItemActivity (Активность элемента)](#1-itemactivity-активность-элемента)
2. [CustomAdapter (Пользовательский адаптер)](#2-customadapter-пользовательский-адаптер)
3. [PlacesActivity (Активность мест)](#3-placesactivity-активность-мест)
4. [MapActivity (Активность карты)](#4-mapactivity-активность-карты)
5. [ItemsActivity (Активность предметов)](#5-itemsactivity-активность-предметов)
6. [AuthActivity (Активность авторизации)](#6-authactivity-активность-авторизации)
7. [MainActivity (Главная активность)](#7-mainactivity-главная-активность)
8. [DbHelper (Помощник базы данных)](#8-dbhelper-помощник-базы-данных)
9. [VisaAdapter (Адаптер визы)](#9-visaadapter-адаптер-изы)
10. [User (Пользователь)](#10-user-пользователь)
11. [Visa (Виза)](#11-visa-виза)
12. [ItemsAdapter (Адаптер предметов)](#12-itemsadapter-адаптер-предметов)
13. [Item (Предмет)](#13-item-предмет)
14. [AddVisaActivity (Активность добавления визы)](#14-addvisaactivity-активность-добавления-изы)
15. [AccountActivity (Активность аккаунта)](#15-accountactivity-активность-аккаунта)

---

### 1. **ItemActivity (Активность элемента)**

- `title`: Заголовок TextView.
- `text`: Текст TextView.
- `backButton`: Кнопка возврата.
- `onCreate()`: Метод создания активности.
- `onClickBack()`: Метод обработки нажатия кнопки возврата.

### 2. **CustomAdapter (Пользовательский адаптер)**

- `binding`: Связь ArrayAdapter с TextView.
- `getView()`: Метод получения представления адаптера.

### 3. **PlacesActivity (Активность мест)**

- `accountButton`: Кнопка аккаунта.
- `mapButton`: Кнопка карты.
- `itemsButton`: Кнопка предметов.
- `onCreate()`: Метод создания активности.
- `onClickAccount()`: Метод обработки нажатия кнопки аккаунта.
- `onClickMap()`: Метод обработки нажатия кнопки карты.
- `onClickItems()`: Метод обработки нажатия кнопки предметов.

### 4. **MapActivity (Активность карты)**

- `accountButton`: Кнопка аккаунта.
- `mapButton`: Кнопка карты.
- `itemsButton`: Кнопка предметов.
- `onCreate()`: Метод создания активности.
- `onClickAccount()`: Метод обработки нажатия кнопки аккаунта.
- `onClickMap()`: Метод обработки нажатия кнопки карты.
- `onClickItems()`: Метод обработки нажатия кнопки предметов.

### 5. **ItemsActivity (Активность предметов)**

- `recyclerView`: RecyclerView для отображения предметов.
- `gestureDetection`: Обнаружение жестов.
- `onCreate()`: Метод создания активности.
- `onGestureTap()`: Метод обработки жестового касания.

### 6. **AuthActivity (Активность авторизации)**

- `login`: Поле EditText для ввода логина.
- `password`: Поле EditText для ввода пароля.
- `loginButton`: Кнопка входа.
- `linkToRegister`: Ссылка на регистрацию.
- `onCreate()`: Метод создания активности.
- `onClickLogin()`: Метод обработки нажатия кнопки входа.
- `onClickRegisterLink()`: Метод обработки нажатия на ссылку регистрации.

### 7. **MainActivity (Главная активность)**

- `login`: Поле EditText для ввода логина.
- `email`: Поле EditText для ввода email.
- `password`: Поле EditText для ввода пароля.
- `buttonRegistry`: Кнопка для регистрации.
- `linkToAuth`: Ссылка на страницу авторизации.
- `onCreate()`: Метод создания активности.
- `onClickRegister()`: Метод обработки нажатия кнопки регистрации.
- `onClickAuthLink()`: Метод обработки нажатия на ссылку авторизации.

### 8. **DbHelper (Помощник базы данных)**

- `users table`: Таблица пользователей.
- `visas table`: Таблица виз.
- `onCreate(SQLiteDatabase db, int version)`: Метод создания базы данных.
- `onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)`: Метод обновления базы данных.

### 9. **VisaAdapter (Адаптер визы)**

- `VisaViewHolder`: Внутренний класс адаптера для удержания представлений виз.
- `onCreateViewHolder(ViewGroup parent, int viewType)`: Метод создания представления для Visa.
- `onBindViewHolder(VisaViewHolder holder, int position)`: Метод связывания данных с представлением Visa.
- `getItemCount()`: Метод получения количества элементов.

### 10. **User (Пользователь)**

- `login`: Логин пользователя.
- `email`: Электронная почта пользователя.
- `password`: Пароль пользователя.
- `User(String login, String email, String password)`: Конструктор для создания пользователя.

### 11. **Visa (Виза)**

- `login`: Логин пользователя, связанного с визой.
- `country`: Страна визы.
- `visaType`: Тип визы.
- `issueDate`: Дата выдачи визы.
- `expiryDate`: Дата окончания визы.
- `Visa(String login, String country, String visaType, Date issueDate, Date expiryDate)`: Конструктор для создания визы.

### 12. **ItemsAdapter (Адаптер предметов)**

- `MyViewHolder`: Внутренний класс для хранения представлений предметов.
- `onCreateViewHolder(ViewGroup parent, int viewType)`: Метод создания представления для предметов.
- `onBindViewHolder(MyViewHolder holder, int position)`: Метод связывания данных с представлением предметов.
- `getItemCount()`: Метод получения количества предметов.

### 13. **Item (Предмет)**

- `id`: Идентификатор предмета.
- `image`: Изображение предмета.
- `title`: Название предмета.
- `desc`: Описание предмета.
- `text`: Текст предмета.
- `Item(int id, String image, String title, String desc, String text)`: Конструктор для создания предмета.

### 14. **AddVisaActivity (Активность добавления визы)**

- `country`: Поле для ввода страны.
- `visaType`: Поле для выбора типа визы.
- `issueDate`: Поле выбора даты выдачи визы.
- `expiryDate`: Поле выбора даты окончания визы.
- `onClickSubmit()`: Метод обработки нажатия кнопки подтверждения.

### 15. **AccountActivity (Активность аккаунта)**

- `mapButton`: Кнопка карты.
- `placesButton`: Кнопка мест.
- `picturesButton`: Кнопка изображений.
- `onCreate()`: Метод создания активности.
- `onClickMap()`: Метод обработки нажатия кнопки карты.
- `onClickPlaces()`: Метод обработки нажатия кнопки мест.
- `onClickPictures()`: Метод обработки нажатия кнопки изображений.
