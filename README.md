# MinTrianglePath

A Scala application that reads a number triangle from standard input or a file and computes the **minimal path sum**

---

## Features

- Reads a triangle of numbers from the **console** or a **text file**.
- Computes the path with the **minimum sum**.
- Displays the path in a **pretty format**:

---

## Example

### Console Input

```bash
cat << EOF | java -jar MinTrianglePath.jar
7
6 3
3 8 5
11 2 10 9
EOF
Minimal path is: 7 + 6 + 3 + 2 = 18
```

### Input from a File
```bash
java -jar MinTrianglePath.jar < triangle.txt
Minimal path is: 7 + 6 + 3 + 2 = 18
```
---
## Build

### Assembly

```bash
sbt clean assembly
```

This will produce a fat JAR on the root directory called **MinTrianglePath.jar**

---
## Usage

### Interactive Mode (Console)
```bash
java -jar MinTrianglePath.jar
```

Type your triangle line by line and press Enter on an empty line to finish.

### Console Text Input

```bash
cat << EOF | java -jar MinTrianglePath.jar
7
6 3
3 8 5
11 2 10 9
EOF
```

### File Mode
```bash
java -jar MinTrianglePath.jar < triangle.txt
Minimal path is: 7 + 6 + 3 + 2 = 18
```
---
## Project Structure

**Triangle** — represents the triangle and computes the minimal path.

**Node** — represents a single number in the triangle.

**NodePath** — stores a path and its cumulative sum.

**DataLoader** — reads input and validates the triangle.

---
## Testing

```bash
sbt test
```

### Tests include:

- Parsing valid triangles.

- Detecting invalid numbers.

- Detecting malformed triangle shapes.

- Verifying minimal path calculation.