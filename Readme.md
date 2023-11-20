[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/nLyD-dmr)
# Mesh Generator (Assignment #2 Walkthrough)

  - Author: Sébastien Mosser

## How to install?

```
mosser@azrael A2 % mvn install
```

It creates two jars:

  1. `generator/generator.jar` to generate meshes
  2. `visualizer/visualizer.jar` to visualize such meshes as SVG files

## Examples of execution

### Generating a mesh, grid or irregular

```
mosser@azrael A2 % java -jar generator/generator.jar -k grid -h 1080 -w 1920 -p 1000 -s 20 -o img/grid.mesh
mosser@azrael A2 % java -jar generator/generator.jar -k grid -h 1080 -w 1920 -p 1000 -s 20 -o img/irregular.mesh
```

### generating island
```
java -jar island/island.jar -i img/irregular.mesh -o img/lagoon.mesh --mode custom --shape circle --lakes 10 --aquifiers 4 --soil dessertsoil --biomes macanada --altitude mountain --rivers 10 --seed 1
```

One can run the generator with `-help` as option to see the different command line arguments that are available

### To control the city number use --cityNumber <number> parameter

### Generation island with cities and road network
java -jar island/island.jar -i img/irregular.mesh -o img/lagoon.mesh --mode custom --shape triangle --lakes 10 --aquifiers 4 --soil basicsoil --biomes kingsfort --altitude mountain --rivers 1 --seed 1 --cityNumber 8


### Visualizing a mesh, (regular or debug mode)

```
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/grid.mesh -o img/grid.svg          
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/grid.mesh -o img/grid_debug.svg -x
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular.svg   
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular_debug.svg -x
```

Before running create img folder

Shapes: circle, rectangle, triangle
Mode: lagoon, custom
Elevation Profiles: mountain, valley
Soil Profiles: basicsoil, dessertsoil
biomes: kingsfort, macanada



Note: PDF versions of the SVG files were created with `rsvg-convert`.

| Id |    Feature title     |     Who?    |    Start   |     End     | Status |
|:--:|----------------------|-------------|------------|-------------|--------|
|F01 | Aquifrers               | Aman        | 03/18/2023 | 03/22/2023  |    D   |
|F02 | Biomes                  | Pranav      | 03/18/2023 | 03/26/2023  |    D   |
|F03 | Polygon Colors          | Pranav      | 03/18/2023 | 03/22/2023  |    D   |
|F04 | Elevation               | Yousef      | 03/18/2023 | 03/22/2023  |    D   |
|F05 | Lakes                   | Aman        | 03/18/2023 | 03/26/2023  |    D   |
|F06 | Modes (lagoon, etc)     | Yousef      | 03/18/2023 | 03/26/2023  |    D   |
|F07 | Rivers                  | Yousef      | 03/18/2023 | 03/26/2023  |    D   |
|F08 | Shapes                  | Aman        | 03/18/2023 | 03/22/2023  |    D   |
|F09 | Soil                    | Pranav      | 03/18/2023 | 03/26/2023  |    D   |
|F10 | Generate cities         | Yousef      | 04/11/2023 | 04/01/2023  |    D   |
|F11 | Generating road network | Yousef      | 04/11/2023 | 04/04/2023  |    D   |
