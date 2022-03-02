# Info
Smith example is a simple AI tasked with keeping a furnace running so long as there is fuel and resources.

## World
The world for the AI consists of a furnace and a chest. There is no concept of world beyond the knowledge these two things exist.

## Furnace
The furnace is a basic object that contains counters for input, output, and fuel.

## Chest
The chest is a basic object that contains counters for input, output, and fuel items. It has no max limit beyond int itself and only counts what exists.

## AI
The AI is built to work in a simple look of checking the furnace, inserting items, and output items. It will do this until it runs out of input resources or fuel. At which point the AI will end its assigned task. During the runtime it will enter into a waiting state as needed to allow the furnace to cook items. This is to simulate a real AI to some extent.




