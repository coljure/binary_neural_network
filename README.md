# Binary Neural Networks

- Sample project used for [coljure meeting](https://www.meetup.com/es-ES/preview/Coljure/events/245702447).
- The slides can be found from [Google presentation](https://docs.google.com/presentation/d/1_9BT1QwilR3Kt0BtoZyhNWQ9qMKkPEeWyyCW8kNYFPc)
- All networks use supervised learning strategy.
- This project trains neural networks for `or`, `and` and `xor` gates.

## Prerequisites

- [Leiningen][1] 2.0 or above installed.
[1]: https://github.com/technomancy/leiningen
- Clojure 1.8

## Running

To start a web server for the application, run:
```bash
lein run
```
Open your browser and access to: `http://localhost:3000/swagger-ui/index.html`

## Considerations
- The `learning-factor` for all gates is `0.5`
- The Bias is conformed by X0=-1 and umbral (`theta`) value. It starts in `0.4` for each neural network.
- The weight's correction is done by:
```
  wi = wi + 2 * learning-factor * yi_expected * xi;
```
- The umbral value is modified by:
```
theta = theta + 2 * learning-factor * yi_expected * X0;
```
- The active function uses `tanh`.
- The stop criterion is `100` iterations.

## License

Copyright © 2017 Apache
