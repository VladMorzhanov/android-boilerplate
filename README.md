# android_boilerplate

### Android Boilerplate based on MVVM architecture.

## Packages:

### data

Package contains models and DataSource classes to perform CRUD operations. 
Package implements Repository pattern.


### databinding

Package contains BindingAdapter classes and BindingConversions class for custom conversion methods that uses in databinding.


### di

Package contain classes that implement dependency ibjection in application.

ActivityBuilder class creates Subcomponent classes for application Activities and Fragments.
AppComponent - application main dagger component.
AppModule - provides main objects for application as utils, repository provider and etc.
Injector class initializes and performs di in application.
ViewModelProviderFactory - used if the viewmodel has a parameterized constructor.

### notification

Package contain classes for notification management. Also class can call notification delayed and after system start.


### receiver

Package contain android receivers as NetworkReceiver.


### ui

Package contain all ui elements: Activities, Fragments, ViewModels, custom views, adapters.
Each activity stores in its own package and every fragment stores in its own package within activity package.
Each activity and fragment has ViewModel, Module and Provider classes.

### util

Package contain application util classes.


### App

Class extends Application class and this is the main class of application. 
It performs Realm and Injector classes initialization.
