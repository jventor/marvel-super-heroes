# Marvel Heroes 💪🏻

Marvel Heroes es una app para Android que lista algunos de los súperheroes de Marvel con sus características.

 Main                      |  Detail
:-------------------------:|:-------------------------:
![main](https://github.com/jventor/marvel-super-heroes/blob/master/art/main.png) | ![detail](https://github.com/jventor/marvel-super-heroes/blob/master/art/detail.png)

## Características ✨

- Kotlin
- MVVM Clean
- Dagger2
- RxJava2 / Live Data
- Room

## Notas 📝

- La API utilizada para este ejemplo es [Marvel Heroes](https://api.myjson.com/bins/bvyob/)

- Utiliza una arquitectura MVVM Clean.

- Persistencia con Room (se utiza como PK el nombre de los superheroes)

- Se permite marcar como favoritos a los superheroes en la vista detalle, quedando reflejado también en la vista maestro.

- Se ha añadido la posilibidad de puntuar al superheroe en la vista detalle

- Se utiliza la estrategia SSOT (Single Source of Truth): La información mostrada proviened de la db
