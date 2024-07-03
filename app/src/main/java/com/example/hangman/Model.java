package com.example.hangman;


import java.util.Random;
import java.util.TreeMap;

/** This class represents the logic for the game. */
public class Model {

    public static final String[] WORDS = new String[]{"able",
            "about", "account", "acid", "across", "act",
            "addition", "adjustment", "advertisement", "after", "again",
            "against", "agreement", "air", "all", "almost",
            "among", "amount", "amusement", "and", "angle",
            "angry", "animal", "answer", "ant", "any",
            "apparatus", "apple", "approval", "arch", "argument",
            "arm", "army", "art", "as", "at",
            "attack", "attempt", "attention", "attraction", "authority",
            "automatic", "awake", "baby", "back", "bad",
            "bag", "balance", "ball", "band", "base",
            "basin", "basket", "bath", "be", "beautiful",
            "because", "bed", "bee", "before", "behaviour",
            "belief", "bell", "bent", "berry", "between",
            "bird", "birth", "bit", "bite", "bitter",
            "black", "blade", "blood", "blow", "blue",
            "board", "boat", "body", "boiling", "bone",
            "book", "boot", "bottle", "box", "boy",
            "brain", "brake", "branch", "brass", "bread",
            "breath", "brick", "bridge", "bright", "broken",
            "brother", "brown", "brush", "bucket", "building",
            "bulb", "burn", "burst", "business", "but",
            "butter", "button", "by", "cake", "camera",
            "canvas", "card", "care", "carriage", "cart",
            "cat", "cause", "certain", "chain", "chalk",
            "chance", "change", "cheap", "cheese", "chemical",
            "chest", "chief", "chin", "church", "circle",
            "clean", "clear", "clock", "cloth", "cloud",
            "coal", "coat", "cold", "collar", "colour",
            "comb", "come", "comfort", "committee", "common",
            "company", "comparison", "competition", "complete", "complex",
            "condition", "connection", "conscious", "control", "cook",
            "copper", "copy", "cord", "cork", "cotton",
            "cough", "country", "cover", "cow", "crack",
            "credit", "crime", "cruel", "crush", "cry",
            "cup", "cup", "current", "curtain", "curve",
            "cushion", "damage", "danger", "dark", "daughter",
            "day", "dead", "dear", "death", "debt",
            "decision", "deep", "degree", "delicate", "dependent",
            "design", "desire", "destruction", "detail", "development",
            "different", "digestion", "direction", "dirty", "discovery",
            "discussion", "disease", "disgust", "distance", "distribution",
            "division", "do", "dog", "door", "doubt",
            "down", "drain", "drawer", "dress", "drink",
            "driving", "drop", "dry", "dust", "ear",
            "early", "earth", "east", "edge", "education",
            "effect", "egg", "elastic", "electric", "end",
            "engine", "enough", "equal", "error", "even",
            "event", "ever", "every", "example", "exchange",
            "existence", "expansion", "experience", "expert", "eye",
            "face", "fact", "fall", "false", "family",
            "far", "farm", "fat", "father", "fear",
            "feather", "feeble", "feeling", "female", "fertile",
            "fiction", "field", "fight", "finger", "fire",
            "first", "fish", "fixed", "flag", "flame",
            "flat", "flight", "floor", "flower", "fly",
            "fold", "food", "foolish", "foot", "for",
            "force", "fork", "form", "forward", "fowl",
            "frame", "free", "frequent", "friend", "from",
            "front", "fruit", "full", "future", "garden",
            "general", "get", "girl", "give", "glass",
            "glove", "go", "goat", "gold", "good",
            "government", "grain", "grass", "great", "green",
            "grey", "grip", "group", "growth", "guide",
            "gun", "hair", "hammer", "hand", "hanging",
            "happy", "harbour", "hard", "harmony", "hat",
            "hate", "have", "he", "head", "healthy",
            "hear", "hearing", "heart", "heat", "help",
            "high", "history", "hole", "hollow", "hook",
            "hope", "horn", "horse", "hospital", "hour",
            "house", "how", "humour", "ice", "idea",
            "if", "ill", "important", "impulse", "in",
            "increase", "industry", "ink", "insect", "instrument",
            "insurance", "interest", "invention", "iron", "island",
            "jelly", "jewel", "join", "journey", "judge",
            "jump", "keep", "kettle", "key", "kick",
            "kind", "kiss", "knee", "knife", "knot",
            "knowledge", "land", "language", "last", "late",
            "laugh", "law", "lead", "leaf", "learning",
            "leather", "left", "leg", "let", "letter",
            "level", "library", "lift", "light", "like",
            "limit", "line", "linen", "lip", "liquid",
            "list", "little", "living", "lock", "long",
            "look", "loose", "loss", "loud", "love",
            "low", "machine", "make", "male", "man",
            "manager", "map", "mark", "market", "married",
            "mass", "match", "material", "may", "meal",
            "measure", "meat", "medical", "meeting", "memory",
            "metal", "middle", "military", "milk", "mind",
            "mine", "minute", "mist", "mixed", "money",
            "monkey", "month", "moon", "morning", "mother",
            "motion", "mountain", "mouth", "move", "much",
            "muscle", "music", "nail", "name", "narrow",
            "nation", "natural", "near", "necessary", "neck",
            "need", "needle", "nerve", "net", "new",
            "news", "night", "no", "noise", "normal",
            "north", "nose", "not", "note", "now",
            "number", "nut", "observation", "of", "off",
            "offer", "office", "oil", "old", "on",
            "only", "open", "operation", "opinion", "opposite",
            "or", "orange", "order", "organization", "ornament",
            "other", "out", "oven", "over", "owner",
            "page", "pain", "paint", "paper", "parallel",
            "parcel", "part", "past", "paste", "payment",
            "peace", "pen", "pencil", "person", "physical",
            "picture", "pig", "pin", "pipe", "place",
            "plane", "plant", "plate", "play", "please",
            "pleasure", "plough", "pocket", "point", "poison",
            "polish", "political", "poor", "porter", "position",
            "possible", "pot", "potato", "powder", "power",
            "present", "price", "print", "prison", "private",
            "probable", "process", "produce", "profit", "property",
            "prose", "protest", "public", "pull", "pump",
            "punishment", "purpose", "push", "put", "quality",
            "question", "quick", "quiet", "quite", "rail",
            "rain", "range", "rat", "rate", "ray",
            "reaction", "reading", "ready", "reason", "receipt",
            "record", "red", "regret", "regular", "relation",
            "religion", "representative", "request", "respect", "responsible",
            "rest", "reward", "rhythm", "rice", "right",
            "ring", "river", "road", "rod", "roll",
            "roof", "room", "root", "rough", "round",
            "rub", "rule", "run", "sad", "safe",
            "sail", "salt", "same", "sand", "say",
            "scale", "school", "science", "scissors", "screw",
            "sea", "seat", "second", "secret", "secretary",
            "see", "seed", "seem", "selection", "self",
            "send", "sense", "separate", "serious", "servant",
            "sex", "shade", "shake", "shame", "sharp",
            "sheep", "shelf", "ship", "shirt", "shock",
            "shoe", "short", "shut", "side", "sign",
            "silk", "silver", "simple", "sister", "size",
            "skin", "skirt", "sky", "sleep", "slip",
            "slope", "slow", "small", "smash", "smell",
            "smile", "smoke", "smooth", "snake", "sneeze",
            "snow", "so", "soap", "society", "sock",
            "soft", "solid", "some", "son", "song",
            "sort", "sound", "soup", "south", "space",
            "spade", "special", "sponge", "spoon", "spring",
            "square", "stage", "stamp", "star", "start",
            "statement", "station", "steam", "steel", "stem",
            "step", "stick", "sticky", "stiff", "still",
            "stitch", "stocking", "stomach", "stone", "stop",
            "store", "story", "straight", "strange", "street",
            "stretch", "strong", "structure", "substance", "such",
            "sudden", "sugar", "suggestion", "summer", "sun",
            "support", "surprise", "sweet", "swim", "system",
            "table", "tail", "take", "talk", "tall",
            "taste", "tax", "teaching", "tendency", "test",
            "than", "that", "the", "then", "theory",
            "there", "thick", "thin", "thing", "this",
            "thought", "thread", "throat", "through", "through",
            "thumb", "thunder", "ticket", "tight", "till",
            "time", "tin", "tired", "to", "toe",
            "together", "tomorrow", "tongue", "tooth", "top",
            "touch", "town", "trade", "train", "transport",
            "tray", "tree", "trick", "trouble", "trousers",
            "true", "turn", "twist", "umbrella", "under",
            "unit", "up", "use", "value", "verse",
            "very", "vessel", "view", "violent", "voice",
            "waiting", "walk", "wall", "war", "warm",
            "wash", "waste", "watch", "water", "wave",
            "wax", "way", "weather", "week", "weight",
            "well", "west", "wet", "wheel", "when",
            "where", "while", "whip", "whistle", "white",
            "who", "why", "wide", "will", "wind",
            "window", "wine", "wing", "winter", "wire",
            "wise", "with", "woman", "wood", "wool",
            "word", "work", "worm", "wound", "writing",
            "wrong", "year", "yellow", "yes", "yesterday",
            "you", "young", "Bernhard", "Android"
    };

    /** The number of mistakes the user can make. */
    public static int MAXNUMBEROFMISTAKES = 7;
    /** Number of rounds. */
    private int round;
    /** RNG. */
    private Random random;
    /** Current word the user must guess. */
    private String currWord;
    /** The words that the user has inputted. */
    private String wordInputted;
    /** Number of characters revealed. */
    private int numberOfCharactersRevealed;
    /** Number of characters the user has entered that are wrong. */
    private int numberOfMistakes;
    /** Pause game status. */
    private boolean pauseGame;
    /** Game over. */
    private boolean gameOver;
    /** Characters that the user has entered, true if they are correct.
     * Otherwise, false.
     */
    private TreeMap<Character, Integer> charactersUsed;
    /** New round. */
    private boolean newRound;

    /** Creates a new Model object. */
    public Model() {
        newGame();
    }

    /** Makes a new game. */
    public void newGame() {
        round = 0;
        numberOfCharactersRevealed = 0;
        numberOfMistakes = 0;
        random = new Random();
        pauseGame = false;
        gameOver = false;
        charactersUsed = new TreeMap<>();
        generateNewWord();
    }

    /** Updates the model for the next round. */
    public void nextRound() {
        generateNewWord();
        round += 1;
        numberOfCharactersRevealed = 0;
        numberOfMistakes = 0;
        charactersUsed = new TreeMap<>();
        newRound = false;
    }

    /** Generates new currWord and wordInputted. */
    private void generateNewWord() {
        currWord = generateRandomWord();
        wordInputted = generateBlankWord();
    }

    /** Returns a random word in WORDS. */
    private String generateRandomWord() {
        return WORDS[random.nextInt(851)];
    }

    /** Generate a blank word of the same length as currWord. */
    private String generateBlankWord() {
        StringBuilder blankWord = new StringBuilder();
        for (int i = 0; i < currWord.length(); i++) {
            blankWord.append("_");
        }

        return blankWord.toString();
    }

    public void pauseGame() {
        pauseGame = true;
    }

    public void resumeGame() {
        pauseGame = false;
    }

    /** User input. */
    public void userInput(char character) {

        // Make it so that user can't enter the same characters.
        if (!charactersUsed.containsKey(character) && !gameOver && !pauseGame) {

            StringBuilder wordInputted = new StringBuilder();

            boolean characterInWord = false;

            for (int i = 0; i < currWord.length(); i++) {

                if (currWord.charAt(i) == character) {
                    wordInputted.append(character);
                    numberOfCharactersRevealed += 1;
                    characterInWord = true;
                } else {
                    if (this.wordInputted.charAt(i) != '_') {
                        wordInputted.append(this.wordInputted.charAt(i));
                    } else {
                        wordInputted.append("_");
                    }

                }
            }

            this.wordInputted = wordInputted.toString();

            if (!characterInWord) {
                numberOfMistakes += 1;
                charactersUsed.put(character, -1);
            } else {
                charactersUsed.put(character, 1);
            }


            if (numberOfCharactersRevealed == currWord.length()) {
                newRound = true;
            }

            if (numberOfMistakes == MAXNUMBEROFMISTAKES) {
                gameOver = true;
            }

        }
    }

    /** Returns the word inputted, in a certain format. */
    public String getWordInputted() {
        StringBuilder formattedWordInput = new StringBuilder();
        for (int i = 0; i < wordInputted.length(); i++) {
            formattedWordInput.append(wordInputted.charAt(i));
            formattedWordInput.append(" ");
        }
        formattedWordInput.deleteCharAt(formattedWordInput.length() - 1);
        return formattedWordInput.toString();
    }

    /** Returns true iff the char character is a correct character input.
     * Otherwise, returns false. */
    public int checkCharacterCorrect(char character) {
        if (charactersUsed.containsKey(character)) {
            return charactersUsed.get(character);
        }
        return 0;

    }

    /** Returns numberOfMistakes. */
    public int getNumberOfMistakes() {
        return numberOfMistakes;
    }

    /** Returns pauseGame. */
    public boolean isPauseGame() {
        return pauseGame;
    }

    /** Returns newRound. */
    public boolean isNewRound() {
        return newRound;
    }

    /** Returns round. */
    public int getRound() {
        return round;
    }

    /** Returns gameOver. */
    public boolean isGameOver() {
        return gameOver;
    }

    /** Returns the current word. */
    public String getCurrWord() {
        return currWord;
    }
}
